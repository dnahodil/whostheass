package com.fgi.whostheass.game

class AssRound extends Round {

	AssRound(createdOrder, firstMove, players) {

		super(createdOrder, firstMove, players)
	}

	@Override
	def getPlayedCards(player) {

		player.playAssRound(playersWhoHavePlayed, moves, playersStillToPlay)
	}

	@Override
	def getWinner() {

		def lastMax

		moves.tail().each {

			if (!lastMax || it.card.points >= lastMax.card.points)
				lastMax = it
		}

		return lastMax.player
	}
}
