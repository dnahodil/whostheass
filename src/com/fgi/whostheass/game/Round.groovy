package com.fgi.whostheass.game

import com.fgi.whostheass.move.*
import com.fgi.whostheass.player.OpponentView

class Round {

	static def roundCount = 0

	def id
	def allPlayers
	def playersWhoHavePlayedViews = []
	def currentPlayerView
	def playersStillToPlayViews = []
	def moves = []

	def Round(firstMove, players) {

		id = roundCount++

		allPlayers = players

		loadPlayerViews()

		playMove firstMove
	}

	def loadPlayerViews() {

		playersWhoHavePlayedViews << new OpponentView(playLeader)
		remainingPlayers.each{ playersStillToPlayViews << new OpponentView(it) }
	}

	def play() {

		remainingPlayers.each {

			updatePlayerViews()

			def move = getPlayerMove(it)

			if (!move.canPlayOn(this)) throw new InvalidMoveException("Can't $move on $this")

			playMove move
		}

		return winner
    }

	def getPlayerMove(currentPlayer) {

		def cardsPlayed = getPlayedCards(currentPlayer)

		Move.from(
			currentPlayer,
			cardsPlayed
		)
	}

	void updatePlayerViews() {

		if (currentPlayerView) playersWhoHavePlayedViews << currentPlayerView

		currentPlayerView = playersStillToPlayViews.first()
		playersStillToPlayViews = playersStillToPlayViews.drop(1)
	}

	void playMove(move) {

		moves << move
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
			moves,
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

	static def forPlayers(players) {

		def leader = players.first()
		def playersRemaining = players.tail()

		def firstMove = Move.from(
			leader,
			leader.startRound(playersRemaining)
		)

		if (!firstMove.canLead()) throw new InvalidMoveException("Cannot lead with $firstMove")

		switch(firstMove.class) {

			case LeadAss:
				return new AssRound(firstMove, players)

			case Pass: PlayJokerOnAss:
				throw new InvalidMoveException("Cannot lead by passing or playing Joker on the Ass")

			default:
				return new Round(firstMove, players)
		}
	}
}
