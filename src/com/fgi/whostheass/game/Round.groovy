package com.fgi.whostheass.game

import com.fgi.whostheass.move.*

class Round {

	static def trickCount = 0

	def id
	def playLeader
	def players
	def moves = []

	def Round(createdOrder, firstMove, players, playLeader) {

		id = createdOrder

		moves << firstMove
		this.players = players
		this.playLeader = playLeader
	}

	def play() {

		players.each {

			def move = getPlayerMove(it)

			if (move.canPlayOn(this)) {

				moves << move
			}
			else {

				throw new InvalidMoveException("Can't play $move on $this")
			}
		}

		return winner
    }

	def getPlayerMove(currentPlayer) {

		Move._moveForCards currentPlayer.playNormalRound(
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

		moves.last().player
	}

	@Override
	public String toString() {

		"Round #$id"
	}

	static def fromMove(leadMove, players, playLeader) {

		trickCount++

		switch(leadMove.class) {

			case LeadAss:
				return new AssRound(trickCount, leadMove, players, playLeader)

			case Pass: PlayJokerOnAss:
				throw new InvalidMoveException("Cannot lead by passing or playing Joker on the Ass")

			default:
				return new Round(trickCount, leadMove, players, playLeader)
		}
	}
}
