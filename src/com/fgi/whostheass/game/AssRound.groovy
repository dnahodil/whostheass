package com.fgi.whostheass.game

class AssRound extends Round {

	AssRound(createdOrder, firstMove, players, playLeader) {

		super(createdOrder, firstMove, players, playLeader)
	}

	@Override
	def getPlayerMove(player) {

		player.playAssRound(playersWhoHavePlayed, moves, playersStillToPlay)
	}

	@Override
	def getWinner() {

		def lastMax

		moves[1..-1].each {

			println it

			if (it.card.value.points >= lastMax.card.value.points)
				lastMax = it
		}

		println "Winner is $lastMax"
	}

	@Override
	public String toString() {

		"Round #$id (Ass played)"
	}
}
