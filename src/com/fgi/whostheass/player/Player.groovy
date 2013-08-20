package com.fgi.whostheass.player

import com.fgi.whostheass.move.InvalidMoveException

import static com.fgi.whostheass.cards.CardImpl.*

class Player {

	static def playerCount = 0

	def id
	def strategy
	def cards = []

	Player() {

		id = playerCount++
	}

	def playNormalRound(playersWhoHavePlayed, movesPlayed, playersStillToPlay) {

		strategy.playNormalRound(cards, playersWhoHavePlayed, movesPlayed, playersStillToPlay)
	}

	def playAssRound(playersWhoHavePlayed, movesPlayed, playersStillToPlay) {

		strategy.playAssRound(cards, playersWhoHavePlayed, movesPlayed, playersStillToPlay)
	}

	def startRound(playersStillToPlay, canLeadAss) {

		strategy.startRound(cards, playersStillToPlay, canLeadAss)
	}

	void dealCard(card) {

		cards << card
		sortCards()
	}

	void pickUpCards(cardsPickedUp) {

		cards.addAll cardsPickedUp
		sortCards()
	}

	void useCards(cardsUsed) {

		cardsUsed.each {
			cardPlayed ->

			if (!cards.contains(cardPlayed)) throw new InvalidMoveException("$this cannot use card $cardPlayed as it is not present in $cards")

			cards.remove cardPlayed
		}
	}

	def hasTheAss() {

		cards.find{ it == Ass } as boolean
	}

	def sortCards() {

		cards = cards.sort{ it.points }
	}

	def getPoints() {

		cards ? cards.sum{ it.points } : 0
	}

	@Override
	public String toString() {

		"Player " + ('A'..'Z')[id]
	}
}
