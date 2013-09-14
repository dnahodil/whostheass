package com.fgi.whostheass.move

class PlayMultiple extends Move {

	@Override
	def canPlayOn(round) {

		(round.moves.first() instanceof PlayMultiple)
	}

	@Override
	def canWin() {

		true
	}
}
