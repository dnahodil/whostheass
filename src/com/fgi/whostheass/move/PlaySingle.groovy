package com.fgi.whostheass.move

class PlaySingle extends Move {
	@Override
	def canPlayOn(round) {
		round.firstMove instanceof PlaySingle || round.firstMove instanceof LeadAss
	}

	@Override
	def canWin() {
		true
	}
}