package com.fgi.whostheass.move

class PlayMultiple extends Move {

	@Override
	def canPlayOn(round) {

		round.firstMove instanceof PlayMultiple
	}

	@Override
	def canWin() {

		true
	}
}
