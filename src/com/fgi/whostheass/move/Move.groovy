package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

import static com.fgi.whostheass.cards.CardImpl.*

abstract class Move {

	def cards
	def player

	abstract boolean canPlayOn(Round round)

	abstract boolean canWin()

	def canLead() {

		true
	}

	static def from(player, cards) {

		if (!validPlay(cards)) throw new InvalidMoveException("It is not a valid move to play these cards: $cards")

		def move = _moveFor(player, cards)

		player.useCards(cards)

		return move
	}

	static def validPlay(cards) {

		_assIsAlone(cards) &&
			_areAllSameValue(cards)
	}

	static def _containsAss(cards) {

		cards.find { it == Ass }
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
			} else {

				if (it != firstValue && it != Joker) {

					allSame = false
				}
			}
		}

		return allSame
	}

	static def _moveFor(player, cards) {

		def args = [
			player: player,
			cards: cards
		]

		switch (cards.size()) {
			case 0:
				return new Pass(args)

			case 1:
				return _containsAss(cards) ?
					new LeadAss(args) :
					new PlaySingle(args)

			default:
				return new PlayMultiple(args)
		}
	}


	@Override
	public String toString() {

		"$player played $cards"
	}
}
