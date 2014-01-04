package com.fgi.whostheass.move

import com.fgi.whostheass.game.AssRound
import com.fgi.whostheass.move.exceptions.InvalidLeadException
import com.fgi.whostheass.move.exceptions.InvalidMoveException

import static com.fgi.whostheass.cards.Card.*

abstract class Move {

	def cards
	def player

	abstract def canPlayOn(round)

	abstract def canWin()

	def firstCard() {

		cards.first()
	}

	def getValue() {

		firstCard()
	}

	def canLead() {

		true
	}

	def cardsUsed() {

		cards
	}

	static def from(player, cards, round = null) {

		_validatePlay(cards)

		def move = _moveFor(player, cards, round)

		if (round) {
			if (!move.canPlayOn(round))
				throw new InvalidMoveException(move, round)
		}
		else {
			if (!move.canLead())
				throw new InvalidLeadException(move)
		}

		player.useCards move.cardsUsed()

		return move
	}

	static def _validatePlay(cards) {

		def validPlay = _areAllSameValue(cards)

		if (!validPlay) throw new InvalidMoveException("It is not a valid move to play these cards: $cards")
	}

	static def _containsAss(cards) {

		cards.find { it == Ass }
	}

	static def _containsJoker(cards) {

		cards.find { it == Joker }
	}

	static def _areAllSameValue(cards) {

		def sortedCards = cards.sort()
		def allSame = true
		def firstValue = sortedCards.firstIfAny()

		sortedCards.each {
			if (it != firstValue && it != Joker) {
				allSame = false
			}
		}

		return allSame
	}

	static def _moveFor(player, cards, round = null) {

		cards = cards.sort()

		// Weed out invalid plays, and play Joker on Ass
		if (round && round instanceof AssRound) {

			switch(cards.size()) {
				case 0:
					throw new InvalidMoveException("Can't pass on an Ass round. ($player)")

				case 1:
					if (_containsJoker(cards))
						throw new InvalidMoveException("Can't play Joker on its own on an AssRound. ($player)")
					break;

				case 2:
					if (_containsJoker(cards))
						return new PlayJokerOnAss(player: player, cards: cards, jokerValue: cards.first())
					break;

				default:
					throw new InvalidMoveException("Can't use $cards to make move on Ass round. ($player)")
			}
		}

		def args = [
			player: player,
			cards: cards
		]

		switch (cards.size()) {
			case 0:
				return new Pass(args)

			case 1:
				return _containsAss(cards) ? new LeadAss(args) : new PlaySingle(args)

			default:
				return new PlayMultiple(args)
		}
	}

	@Override
	public String toString() {

		"$player played ${this.class.simpleName}(${cards.join(", ")})"
	}
}
