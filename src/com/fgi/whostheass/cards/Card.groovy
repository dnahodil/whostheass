package com.fgi.whostheass.cards

import static com.fgi.whostheass.game.Rules.getNumberOfEachNumberInDeck
import static com.fgi.whostheass.game.Rules.getNumberOfJokersInDeck

public enum Card {

	One(1),
	Two(2),
	Three(3),
	Four(4),
	Five(5),
	Six(6),
	Seven(7),
	Eight(8),
	Nine(9),
	Ten(10),
	Eleven(11),
	Twelve(12),
	Thirteen(13),

	Joker(14),

	Ass(20)

	int points

	Card(p) {

		points = p
	}

	@Override
	public String toString() {

		switch(this) {
			case [Joker, Ass]:
				return name()
			default:
				return points
		}
	}

	static def getJokers() {

		def jokers = []

		numberOfJokersInDeck.times {
			jokers << Joker
		}

		return jokers
	}

	static def getNumberCards() {

		def numbers = []

		(One..Thirteen).each { cardValue ->

			numberOfEachNumberInDeck.times {
				numbers << cardValue
			}
		}

		return numbers
	}
}
