package com.fgi.whostheass.move

import com.fgi.whostheass.game.AssRound
import com.fgi.whostheass.game.Round

class PlayJokerOnAss extends Move {

	def jokerValue

	@Override
	boolean canPlayOn(Round round) {

		return round instanceof AssRound
	}

	@Override
	boolean canWin() {

		return true
	}

	@Override
	def canLead() {

		false
	}

	@Override
	public String toString() {

		"$player played $cards as a $jokerValue"
	}
}
