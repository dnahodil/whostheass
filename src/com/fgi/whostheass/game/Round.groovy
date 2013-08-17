package com.fgi.whostheass.game

import com.fgi.whostheass.move.*

class Round {

	static def roundCount = 0

	def id
	def playLeader
	def players
	def moves = []

	def Round(createdOrder, firstMove, players) {

		id = createdOrder

		if (!firstMove.canLead()) throw new InvalidMoveException("Cannot lead with $move")

		moves << firstMove
		this.players = players
		this.playLeader = players.first()
	}

	def play() {

		players.tail().each { // First player has made their move already

			def move = getPlayerMove(it)

			if (!move.canPlayOn(this)) throw new InvalidMoveException("Can't $move on $this")

			moves << move
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

	def getPlayedCards(currentPlayer) {

		currentPlayer.playNormalRound(
			playersWhoHavePlayed,
			moves,
			playersStillToPlay
		)
	}

	def getPlayersWhoHavePlayed() {

		null
	}

	def getPlayersStillToPlay() {

		null
	}

	def getWinner() {

		moves.findAll{ it.canWin() }.last().player
	}

	@Override
	public String toString() {

		"Round #$id. " + moves.join(" > ")
	}

	static def forPlayers(players) {

		roundCount++

		def leader = players.first()
		def playersRemaining = players.tail()

		def move = Move.from(
			leader,
			leader.startRound(playersRemaining)
		)

		switch(move.class) {

			case LeadAss:
				return new AssRound(roundCount, move, players)

			case Pass: PlayJokerOnAss:
				throw new InvalidMoveException("Cannot lead by passing or playing Joker on the Ass")

			default:
				return new Round(roundCount, move, players)
		}
	}
}
