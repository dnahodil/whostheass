package com.fgi.whostheass.move

class InvalidLeadException extends InvalidMoveException {

	InvalidLeadException(move) {

		super("Cannot lead with $move")
	}
}
