package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

abstract class Move {

	abstract boolean canPlayOn(Round round)

	static def fromCards(cards) {

		if (!validPlay(cards)) throw new InvalidMoveException("It is not a valid move to play these cards: $cards")

		return null
	}

	static def validPlay(cards) {

		return false
	}
}
