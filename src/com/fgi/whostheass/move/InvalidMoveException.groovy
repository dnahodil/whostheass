package com.fgi.whostheass.move

class InvalidMoveException extends RuntimeException {

	InvalidMoveException(message) {

		super(message as String)
	}

	InvalidMoveException(move, previousMove) {

		super("Invalid move: $move on $previousMove")
	}
}
