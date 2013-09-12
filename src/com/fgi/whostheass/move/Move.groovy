package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

import static com.fgi.whostheass.cards.Card.*

abstract class Move {

	def cards
	def player

	abstract boolean canPlayOn(Round round)

	abstract boolean canWin()

	def canLead() {

		true
	}

	static def from(player, cards, round = null) {

		_validatePlay(cards)

		def move = _moveFor(player, cards, round)

		player.useCards cards

		return move
	}

	static def _validatePlay(cards) {

		def validPlay = _areAllSameValue(cards)

		if (!validPlay) throw new InvalidMoveException("It is not a valid move to play these cards: $cards")
	}

	static def _containsAss(cards) {

		cards.find { it == Ass }
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

	static def _moveFor(player, cards, round) {

		println "_moveFor() Called with args $player, $cards and $round"

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
