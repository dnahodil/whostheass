package com.fgi.whostheass.move

import static com.fgi.whostheass.cards.Card.Joker
import com.fgi.whostheass.game.AssRound

class PlayJokerOnAss extends Move {
	def jokerValue

	@Override
	def getValue() {
		jokerValue
	}

	@Override
	def canPlayOn(round) {
		return round instanceof AssRound
	}

	@Override
	def canWin() {
		true
	}

	@Override
	def canLead() {
		false
	}

	@Override
	def cardsUsed() {
		[Joker]
	}

	@Override
	public String toString() {
		"$player played $cards as a $jokerValue"
	}
}
