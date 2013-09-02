package com.fgi.whostheass.game

import com.fgi.whostheass.move.InvalidLeadException
import com.fgi.whostheass.move.InvalidMoveException
import com.fgi.whostheass.move.LeadAss
import com.fgi.whostheass.move.Move
import com.fgi.whostheass.move.Pass
import com.fgi.whostheass.player.OpponentViewImpl

class Round {

	static def roundCount = 0

	def id
	def allPlayers
	def playersWhoHavePlayedViews = []
	def currentPlayerView
	def playersStillToPlayViews = []
	def moves = []

	Round(firstMove, players) {

		id = roundCount++

		allPlayers = players

		loadPlayerViews()

		playMove firstMove
	}

	def loadPlayerViews() {

		playersWhoHavePlayedViews << new OpponentViewImpl(playLeader)
		remainingPlayers.each{ playersStillToPlayViews << new OpponentViewImpl(it) }
	}

	def play() {

		remainingPlayers.each {

			updatePlayerViews()

			def move = getPlayerMove(it)

			if (!move.canPlayOn(this)) throw new InvalidMoveException(move, this)

			playMove move
		}

		return winner
	}

	def getPlayerMove(currentPlayer) {

		def cardsPlayed = getPlayedCards(currentPlayer)

		Move.from(
			currentPlayer,
			cardsPlayed,
			this
		)
	}

	void updatePlayerViews() {

		if (currentPlayerView) playersWhoHavePlayedViews << currentPlayerView

		currentPlayerView = playersStillToPlayViews.first()
		playersStillToPlayViews = playersStillToPlayViews.tail()
	}

	void playMove(move) {

		moves << move
	}

	def getMovesAsCardArrays() {

		moves.collect{ it.cards }
	}

	def getPlayLeader() {

		allPlayers.first()
	}

	def getRemainingPlayers() {

		allPlayers.tail()
	}

	def getPlayedCards(currentPlayer) {

		currentPlayer.playNormalRound(
			playersWhoHavePlayedViews,
			movesAsCardArrays,
			playersStillToPlayViews
		)
	}

	def getWinner() {

		moves.findAll{ it.canWin() }.last().player
	}

	@Override
	public String toString() {

		"Round #$id. " + moves.join(" > ")
	}

	static def forPlayers(players, canLeadAss) {

		def leader = players.first()
		def playersRemaining = players.tail()

		def firstMove = Move.from(
			leader,
			leader.startRound(playersRemaining, canLeadAss)
		)

		if (!firstMove.canLead()) throw new InvalidLeadException(firstMove)

		switch (firstMove.class) {

			case LeadAss:
				if (!canLeadAss) throw new InvalidLeadException(firstMove)
				return new AssRound(firstMove, players)

			case Pass: PlayJokerOnAss:
				throw new InvalidLeadException(firstMove)

			default:
				return new Round(firstMove, players)
		}
	}
}
