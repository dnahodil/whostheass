package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.move.Move
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

		Collections.shuffle players // Random seating order
    }

    def play() {

		def nextPlayLeader = players.find{ it.hasTheAss() }

		println "nextPlayLeader = $nextPlayLeader"
		println "players = $players"

		players = playersStartingWith(nextPlayLeader)

		println "players = $players"

		while (nextPlayLeader.cards.size()) {

			def cardsLead = nextPlayLeader.startRound()

			def move = Move.from(nextPlayLeader, cardsLead)

			def round = Round.from(move, players)

			def winner = round.play()

			println "$round was won by $winner"

			nextPlayLeader = winner
        }

		playersInOrderAdded.each {

			println "$it finished with ${it.points} points"
		}
    }

	def getPlayersInOrderAdded() {

		players.sort{ it.id }
	}

	def playersStartingWith(playStarter) {

		def idx = players.indexOf(playStarter)

		idx == 0 ? players : players[idx..-1] + players[0..idx-1]
	}
}
