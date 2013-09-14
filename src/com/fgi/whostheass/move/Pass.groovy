package com.fgi.whostheass.move

import com.fgi.whostheass.game.AssRound

class Pass extends Move {

	@Override
	def value() {

		null
	}

	@Override
	def canPlayOn(round) {

		!(round instanceof AssRound)
	}

	@Override
	def canWin() {

		false
	}

	@Override
	def canLead() {

		false
	}
}
