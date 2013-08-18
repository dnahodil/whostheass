package com.fgi.whostheass.game

class AssRound extends Round {

	AssRound(firstMove, players) {

		super(firstMove, players)
	}

	@Override
	def getPlayedCards(player) {

		player.playAssRound(
			playersWhoHavePlayedViews,
			moves,
			playersStillToPlayViews
		)
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
