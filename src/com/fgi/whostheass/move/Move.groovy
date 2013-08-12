package com.fgi.whostheass.move

import static com.fgi.whostheass.cards.Card.*
import com.fgi.whostheass.game.Round

abstract class Move {

	def cards
	abstract boolean canPlayOn(Round round)

	static def fromCards(cards) {

		if (!validPlay(cards)) throw new InvalidMoveException("It is not a valid move to play these cards: $cards")

		_moveForCards(cards)
	}

	static def validPlay(cards) {

		_assIsAlone(cards) &&
		_areAllSameValue(cards)
	}

	static def _containsAss(cards) {

		cards.find{it == Ass}
	}

	static def _assIsAlone(cards) {

		if (_containsAss(cards)) {

			return cards.size() == 1
		}

		return true
	}

	static def _areAllSameValue(cards) {

		def allSame = true
		def firstValue

		cards.sort().each {

			if (!firstValue) {

				firstValue = it
			}
			else {

				if (it != firstValue && it != Joker) {

					allSame = false
				}
			}
		}

		return allSame
	}

	static def _moveForCards(cards) {

		switch(cards.size()) {
			case 0:
				return new Pass()

			case 1:
				return _containsAss(cards) ? new LeadAss() : new PlaySingle(cards: cards)

			default:
				return new PlayMultiple(cards: cards)
		}
	}
}
