package com.fgi.whostheass.move.exceptions

class InvalidMoveException extends RuntimeException {
	InvalidMoveException(message) {
		super(message as String)
	}

	InvalidMoveException(move, round) {
		super("Invalid move: $move on $round")
	}
}
