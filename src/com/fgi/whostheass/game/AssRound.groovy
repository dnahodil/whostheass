package com.fgi.whostheass.game

class AssRound extends Round {

	AssRound(id, firstMove, players) {

		super(id, firstMove, players)
	}

	@Override
	def play() {

		def winner = super.play()

		winner.pickUpCards allCardsPlayed

		return winner
	}

	@Override
	def getPlayedCards(player) {

		player.playAssRound(
			playersWhoHavePlayedViews,
			movesAsCardArrays,
			playersStillToPlayViews
		)
	}

	@Override
	void notifyPlayerOfOutcome(player) {

		player.updateAfterAssRound(
			opponentViewsFor(allPlayers),
			moves*.cards,
			opponentViewFor(winner)
		)
	}

	@Override
	def getWinner() {

		def lastMax

		moves.tail().each { move ->

			if (!lastMax || move.value >= lastMax.value) lastMax = move
		}

		return lastMax.player
	}

	def getAllCardsPlayed() {

		moves*.cards.flatten()
	}

	@Override
	public String toString() {

		super.toString() + " (Ass Round)"
	}
}
