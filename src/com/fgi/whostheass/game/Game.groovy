package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.player.Player
import static Rules.*

class Game {

    def players = []
    def state

    Game(playerStrategies) {

        if (!validNumberOfPlayers.contains(playerStrategies.size())) throw new IllegalStateException("Must have $validNumberOfPlayers Players")

        initPlayers(playerStrategies)

        new Deck().deal(players)
    }

    def initPlayers(playerStrategies) {

        playerStrategies.each {
            strat ->

            players << new Player(name: nextPlayerName, strategy: strat)
        }
    }

    def getNextPlayerName() {

        return "Player ${('A'..'Z')[players.size()]}"
    }

    def play() {

        while (!state.winner) {

            state = new Trick().play(state)
        }
    }
}
