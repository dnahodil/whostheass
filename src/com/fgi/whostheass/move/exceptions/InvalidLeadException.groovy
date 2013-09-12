package com.fgi.whostheass.move.exceptions

class InvalidLeadException extends InvalidMoveException {

	InvalidLeadException(move) {

		super("Cannot lead with $move")
	}
}
