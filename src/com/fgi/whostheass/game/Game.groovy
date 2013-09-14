package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.player.Player

import static com.fgi.whostheass.game.Rules.getValidNumberOfPlayers

class Game {

	def players = []

	Game(playerStrategies) {

		if (!validNumberOfPlayers.contains(playerStrategies.size()))
			throw new IllegalStateException("Must have $validNumberOfPlayers Players. Tried to play game with ${playerStrategies.size()}.")

		initPlayers(playerStrategies)

		new Deck().deal(players)

		sortPlayersStaringWith playerWithAss
	}

	def initPlayers(playerStrategies) {

		playerStrategies.each {

			players << playerFor(it)
		}

		Collections.shuffle players // Random seating order
	}

	def play() {

		def canLeadAss = true

		while (!anyPlayerHasGoneOut) {

			def round = Round.forPlayers(players, canLeadAss)

			def winner = round.play()

			sortPlayersStaringWith winner
			canLeadAss = !(round instanceof AssRound)

			def canLeadAssString = canLeadAss ? "" : " (Can't lead Ass next round)"

			println "$round. Won by $winner.$canLeadAssString"
		}

		playersInOrderAdded.each {

			println "$it finished with ${it.points} points"
		}

		return players
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

	def playerFor(strategy) {

		new Player(
			id: players.size(),
			strategy: strategy
		)
	}
}
