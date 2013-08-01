package com.fgi.whostheass.game

import com.fgi.whostheass.cards.Deck
import com.fgi.whostheass.player.Player

class Game {

    def players = []
    def rounds

    Game(playerStrategies) {

        playerStrategies.each {

            players << new Player(it)
        }

        def deck = Deck.shuffledDeck()

        deck.deal(players)
    }
}
