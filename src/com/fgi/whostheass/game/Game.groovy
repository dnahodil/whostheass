package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.player.Player

import static com.fgi.whostheass.game.Rules.getValidNumberOfPlayers

class Game {

	def players

	Game(playerStrategies) {

		if (!validNumberOfPlayers.contains(playerStrategies.size()))
			throw new IllegalStateException("Must have $validNumberOfPlayers Players. Tried to play game with ${playerStrategies.size()}.")

		initPlayers(playerStrategies)

		new Deck().deal(players)

		def playerWithAss = players.find { it.hasTheAss() }

		sortPlayersStaringWith(playerWithAss)
	}

	def initPlayers(playerStrategies) {

		players = playerStrategies.collect {

			new Player(strategy: it)
		}

		Collections.shuffle players // Random seating order
	}

	def play() {

		while (!playerHasGoneOut()) {

			def round = Round.forPlayers(players)

			def winner = round.play()

			println "$round. Won by $winner."
		}

		playersInOrderAdded.each {

			println "$it finished with ${it.points} points"
		}
	}

	def playerHasGoneOut() {

		players.find { it.cards.size() == 0 }
	}

	def getPlayersInOrderAdded() {

		players.sort { it.id }
	}

	void sortPlayersStaringWith(playStarter) {

		players = players.dropWhile { it != playStarter } +
			players.takeWhile { it != playStarter }
	}
}
