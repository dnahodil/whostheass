package com.fgi.whostheass.player

class OpponentView {

	int numberOfCards
	boolean includesTheAss

	String playerName

	OpponentView(player) {

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
