package com.fgi.whostheass.move

import com.fgi.whostheass.game.Round

class PlaySingle extends Move {

	@Override
	boolean canPlayOn(Round round) {

		return (round.moves.first() instanceof PlaySingle)
	}
}