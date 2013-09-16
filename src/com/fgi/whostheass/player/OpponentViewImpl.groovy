package com.fgi.whostheass.player

class OpponentViewImpl implements OpponentView {

	final int numberOfCards
	final boolean includesTheAss

	final String playerName

	OpponentViewImpl(player) {

		playerName = "$player"

		numberOfCards = player.cards.size()
		includesTheAss = player.hasTheAss()
	}

	@Override
	public String toString() {

		def cardsInfo = "$numberOfCards in hand"
		def assInfo = includesTheAss ? "including The Ass" : ""

		"(View of $playerName - $cardsInfo $assInfo)"
	}
}
