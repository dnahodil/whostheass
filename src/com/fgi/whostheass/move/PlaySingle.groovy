package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

class PlaySingle extends Move {

	def getCard() {

		return cards.first()
	}

	@Override
	boolean canPlayOn(Round round) {

		def firstMove = round.moves.first()

		return (
		firstMove instanceof PlaySingle ||
			firstMove instanceof LeadAss
		)
	}

	@Override
	boolean canWin() {

		true
	}
}