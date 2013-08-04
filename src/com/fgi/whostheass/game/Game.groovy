package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.player.Player
import static com.fgi.whostheass.game.Rules.*

class Game {

    def players

    Game(playerStrategies) {

        if (!validNumberOfPlayers.contains(playerStrategies.size()))
            throw new IllegalStateException("Must have $validNumberOfPlayers Players. Tried to play game with ${playerStrategies.size()}.")

        initPlayers(playerStrategies)

        new Deck().deal(players)
    }

    def initPlayers(playerStrategies) {

        players = []

        playerStrategies.each {

            players << new Player(strategy: it)
        }

		Collections.shuffle players
    }

    def play() {

		def nextPlayLeader = players.find{ it.hasTheAss() }

		while (nextPlayLeader.cards.size()) {

			def trick = new Trick(playLeader: nextPlayLeader, players: players)

			def winner = trick.play()

			println "$trick was won by $winner"

			nextPlayLeader = winner
        }

		playersInOrderAdded.each {

			println "$it finished with ${it.points} points"
		}
    }

	def getPlayersInOrderAdded() {

		players.sort{ it.id }
	}
}
