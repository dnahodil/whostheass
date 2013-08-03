package com.fgi.whostheass.game

class Trick {

	static def trickCount = 0

	def id
	def playLeader

	def Trick() {

		id = trickCount++
	}

	def play() {

		return playLeader
    }

	@Override
	public String toString() {

		"Trick #$id"
	}
}
