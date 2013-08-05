package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

class PlayMultiple extends Move {

	@Override
	boolean canPlayOn(Round round) {

		return (round.moves.first() instanceof PlayMultiple)
	}
}
