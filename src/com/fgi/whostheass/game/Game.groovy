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

		sortPlayersStaringWith playerWithAss
	}

	def initPlayers(playerStrategies) {

		players = playerStrategies.collect {

			new Player(strategy: it)
		}

		Collections.shuffle players // Random seating order
	}

	def play() {

		while (!anyPlayerHasGoneOut) {

			def round = Round.forPlayers(players)

			def winner = round.play()

			println "$round. Won by $winner."

			sortPlayersStaringWith winner
		}

		playersInOrderAdded.each {

			println "$it finished with ${it.points} points"
		}
	}

	def getAnyPlayerHasGoneOut() {

		players.find { it.cards.size() == 0 }
	}

	def getPlayerWithAss() {

		players.find { it.hasTheAss() }
	}

	def getPlayersInOrderAdded() {

		players.sort { it.id }
	}

	void sortPlayersStaringWith(playStarter) {

		players = players.dropWhile { it != playStarter } +
		          players.takeWhile { it != playStarter }
	}
}
