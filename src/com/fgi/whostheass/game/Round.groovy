package com.fgi.whostheass.game

import com.fgi.whostheass.move.*

class Round {

	static def trickCount = 0

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

		players.each {

			if (it != playLeader) { // Play leader has played already

				def move = getPlayerMove(it)

				if (!move.canPlayOn(this)) throw new InvalidMoveException("Can't play $move on $this")

				moves << move
			}
		}

		return winner
    }

	def getPlayerMove(currentPlayer) {

		def cardsPlayed = getPlayedCards(currentPlayer)

		currentPlayer.useCards(cardsPlayed)

		Move.from(
			currentPlayer,
			cardsPlayed
		)
	}

	def getPlayedCards(currentPlayer) {

		currentPlayer.playNormalRound(
			getPlayersWhoHavePlayed(currentPlayer),
			moves,
			getPlayersStillToPlay(currentPlayer)
		)
	}

	def getPlayersWhoHavePlayed(currentPlayer) {

		null
	}

	def getPlayersStillToPlay(currentPlayer) {

		null
	}

	def getWinner() {

		moves.findAll{ it.canWin() }.last().player
	}

	@Override
	public String toString() {

		"Round #$id. " + moves*.cards.join(" > ")
	}

	static def from(leadMove, players) {

		trickCount++

		switch(leadMove.class) {

			case LeadAss:
				return new AssRound(trickCount, leadMove, players)

			case Pass: PlayJokerOnAss:
				throw new InvalidMoveException("Cannot lead by passing or playing Joker on the Ass")

			default:
				return new Round(trickCount, leadMove, players)
		}
	}
}
