package com.fgi.whostheass.player

class OpponentView {

    int numberOfCards
    boolean includesTheAss

	@Override
	public String toString() {

		def cardsInfo = "$numberOfCards in hand"
		def assInfo = includesTheAss ? " including The Ass" : ""

		cardsInfo + assInfo
	}
}
