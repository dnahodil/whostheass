package com.fgi.whostheass.player

import com.fgi.whostheass.cards.Card

class Player {

    static def playerCount = 0

    def id
    def strategy
	def cards = []

    def Player() {

        id = playerCount++
    }

	def playNormalRound(playersWhoHavePlayed, movesPlayed, playersStillToPlay) {

		strategy.playNormalRound(cards, playersWhoHavePlayed, movesPlayed, playersStillToPlay)
	}

	def playAssRound(playersWhoHavePlayed, movesPlayed, playersStillToPlay) {

		strategy.playAssRound(cards, playersWhoHavePlayed, movesPlayed, playersStillToPlay)
	}

	def startRound(playersStillToPlay) {

		strategy.startRound(cards, playersStillToPlay)
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

		cards.find{ it == Card.Ass } as boolean
	}

	def viewOfHand() {

		new OpponentView(
			numberOfCards: cards.size(),
			includesTheAss: hasTheAss()
		)
	}

	def sortCards() {

		cards = cards.sort{ it.points }
	}

	def getPoints() {

		cards ? cards.sum{ it.points } : 0
	}

    @Override
    public String toString() {

        return "Player ${('A'..'Z')[id]}"
    }
}