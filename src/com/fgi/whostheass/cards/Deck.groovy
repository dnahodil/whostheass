package com.fgi.whostheass.cards

import static com.fgi.whostheass.game.Rules.getNumberOfCardsInHand
import static com.fgi.whostheass.cards.CardImpl.*

class Deck {

	def cards = [] as LinkedList

	Deck() {

		cards.addAll jokers
		cards.addAll numberCards
		// We only add The Ass once we know how many Players we're dealing to

		Collections.shuffle cards
	}

	void deal(players) {

		def numPlayers = players.size()
		def numCardsBeingDealt = numPlayers * numberOfCardsInHand

		insertAss randomLocationFor(numCardsBeingDealt)

		while (players.first().cards.size() < numberOfCardsInHand) {

			players.each {

				it.dealCard nextCard
			}
		}

		players.each {

			println "$it has these Cards: ${it.cards}"
		}
	}

	void insertAss(location) {

		cards.add location, Ass
	}

	def getNextCard() {

		cards.removeFirst()
	}

	static def randomLocationFor(numCardsBeingDealt) {

		new Random().nextInt(numCardsBeingDealt)
	}
}
