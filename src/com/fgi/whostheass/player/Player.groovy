package com.fgi.whostheass.player

import com.fgi.whostheass.hand.Hand

class Player {

    def name
    def strategy
    def hand

    def Player() {

        hand = new Hand()
    }

    def dealCard(card) {

        hand.cards << card
    }

    @Override
    public String toString() {

        return name
    }
}
