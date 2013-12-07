package com.fgi.whostheass.game

import com.fgi.whostheass.move.exceptions.InvalidLeadException
import com.fgi.whostheass.move.exceptions.InvalidMoveException
import com.fgi.whostheass.move.LeadAss
import com.fgi.whostheass.move.Move
import com.fgi.whostheass.player.OpponentViewImpl

class Round {

	static def roundCount = 0

	def id
	def allPlayers
	def opponentViews = [:]
	def playersWhoHavePlayedViews
	def currentPlayerView
	def playersStillToPlayViews
	def moves = []

	Round(firstMove, players) {

		id = roundCount++

		allPlayers = players

		initPlayerViews()

		playMove firstMove
	}

	def initPlayerViews() {

		playersWhoHavePlayedViews = [opponentViewFor(playLeader)]
		playersStillToPlayViews = remainingPlayers.collect{ opponentViewFor(it) }
	}

	def play() {

		remainingPlayers.each {

			updatePlayerViews()

			def move = getPlayerMove(it)

			if (!move.canPlayOn(this)) throw new InvalidMoveException(move, this)

			playMove move
		}

		println "____________________________________________"
		println "playersWhoHavePlayedViews = $playersWhoHavePlayedViews"
		println "currentPlayerView = $currentPlayerView"
		println "playersStillToPlayViews = $playersStillToPlayViews"
		println "^__________________________________________^"

		allPlayers.each {

			notifyPlayerOfOutcome(it)
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

	void notifyPlayerOfOutcome(player) {

		player.updateAfterNormalRound([], moves, opponentViewFor(winner))
	}

	def opponentViewFor(player) {

		if (!opponentViews[player]) {
			opponentViews[player] = new OpponentViewImpl(player)
		}

		return opponentViews[player]
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

		if (firstMove instanceof LeadAss) {
			if (!canLeadAss)
				throw new InvalidLeadException(firstMove)

			return new AssRound(firstMove, players)
		}

		return new Round(firstMove, players)
	}
}
