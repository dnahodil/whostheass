package com.fgi.whostheass.player

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.cards.CardValue

class Player {

    static def playerCount = 0

    def id
    def strategy
	def cards = []

    def Player() {

        id = playerCount++
    }

    def dealCard(card) {

        cards << card
		sortCards()
    }

    def pickUpCards(cards) {

        println "$this picked up $cards"

        cards.addAll cards
		sortCards()
    }

	def hasTheAss() {

		cards.find{ it.value == CardValue.Ass } as boolean
	}

	def viewOfHand() {

		new OpponentView(
			numberOfCards: cards.size(),
			includesTheAss: hasTheAss()
		)
	}

	def sortCards() {

		cards = cards.sort{ it.value.points }
	}

    @Override
    public String toString() {

        return "Player ${('A'..'Z')[id]}"
    }
}
