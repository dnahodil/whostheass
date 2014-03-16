package com.fgi.whostheass.game

import com.fgi.whostheass.move.exceptions.InvalidLeadException
import com.fgi.whostheass.move.LeadAss
import com.fgi.whostheass.move.Move
import com.fgi.whostheass.player.OpponentViewImpl

class Round {

	def id
	def allPlayers
	def playersWhoHavePlayedViews
	def currentPlayerView
	def playersStillToPlayViews
	def moves = []

	Round(id, firstMove, players) {

		this.id = id

		allPlayers = players

		initPlayerViews()

		playMove firstMove
	}

	def initPlayerViews() {

		playersWhoHavePlayedViews = []
		currentPlayerView = opponentViewFor(playLeader)
		playersStillToPlayViews = opponentViewsFor(remainingPlayers)
	}

	void updatePlayerViews() {

		def previousPlayer = allPlayers[playersWhoHavePlayedViews.size()]
		playersWhoHavePlayedViews << opponentViewFor(previousPlayer) // New OpponentView required to represent new hand state

		// Move any remaining players forward
		currentPlayerView = playersStillToPlayViews.firstIfAny()
		playersStillToPlayViews = playersStillToPlayViews.tailIfAny()
	}

	def play() {

		remainingPlayers.each { player ->
			playMove getPlayerMove(player)
		}

		allPlayers.each { player ->
			notifyPlayerOfOutcome player
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

	void playMove(move) {

		moves << move

		updatePlayerViews()
	}

	def getFirstMove() {

		moves.first()
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

		player.updateAfterNormalRound(
			opponentViewsFor(allPlayers),
			moves*.cards,
			opponentViewFor(winner)
		)
	}

	def opponentViewFor(player) {

		new OpponentViewImpl(player)
	}

	def opponentViewsFor(players) {

		players.collect{ opponentViewFor(it) }
	}

	@Override
	public String toString() {

		"Round #$id. " + moves.join(" > ")
	}

	static def forPlayers(id, players, canLeadAss) {

		def leader = players.first()
		def playersRemaining = players.tail()

		def firstMove = Move.from(
			leader,
			leader.startRound(playersRemaining, canLeadAss)
		)

		if (firstMove instanceof LeadAss) {
			if (!canLeadAss)
				throw new InvalidLeadException(firstMove)

			return new AssRound(id, firstMove, players)
		}

		return new Round(id, firstMove, players)
	}
}
