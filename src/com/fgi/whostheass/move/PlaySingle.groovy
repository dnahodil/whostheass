package com.fgi.whostheass.move

class PlaySingle extends Move {

	@Override
	def canPlayOn(round) {

		def firstMove = round.moves.first()

		firstMove instanceof PlaySingle ||firstMove instanceof LeadAss
	}

	@Override
	def canWin() {

		true
	}
}