package com.fgi.whostheass.game

import com.fgi.whostheass.move.MoveType

class Trick {

	static def trickCount = 0

	def id
	def playLeader
	def players
	def moves = []

	def Trick() {

		id = trickCount++
	}

	def play() {

		players.each {

			def move = it.nextMove

			if (move.canPlayOn(this)) {

				moves << [ // Could this be better??
					"player": it,
					"move": move
				]
			}
		}

		return winner
    }

	def getWinner() {

		if (isAssTrick()) {

			// Determine this
		}
		else {

			moves.last().player
		}
	}

	def isAssTrick() {

		moves.first().type == MoveType.LeadAss
	}

	@Override
	public String toString() {

		"Trick #$id"
	}
}
