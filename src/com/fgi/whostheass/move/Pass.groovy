package com.fgi.whostheass.move

import com.fgi.whostheass.game.AssRound
import com.fgi.whostheass.game.Round

class Pass extends Move {

	@Override
	boolean canPlayOn(Round round) {

		!(round instanceof AssRound)
	}

	@Override
	boolean canWin() {

		return false
	}

	@Override
	def canLead() {

		false
	}
}
