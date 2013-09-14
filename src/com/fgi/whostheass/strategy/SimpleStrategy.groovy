package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.player.OpponentView
import static com.fgi.whostheass.cards.Card.*

class SimpleStrategy implements PlayStrategy {

	@Override
	List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		def moveToBeatCards = movesPlayed.findAll{ it.size() }.last()
		def moveToBeatCardPoints = moveToBeatCards.first().points
		def moveToBeatNumberCards = moveToBeatCards.size()

		def possiblePlays = []

		def distinctValidCards = nonAssCardsInHand(cardsInHand).unique()
		def cardsHigherThanThosePlayed = distinctValidCards.dropWhile{ it.points <= moveToBeatCardPoints }

		cardsHigherThanThosePlayed.each{
			card ->

			allCardsWithValueUsingJokers(cardsInHand, card).each{
				cardsWithValue ->

				if (cardsWithValue.size() == moveToBeatNumberCards)
					possiblePlays << cardsWithValue
			}
		}

		possiblePlays << [] // Can always play Pass

		return possiblePlays.first() as List<Card>
	}

	@Override
	List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		def cardToPlay = cardsInHand.first() // Play lowest card

		if (cardToPlay == Joker) {

			return [Joker, One]
		}

		return [cardToPlay]
	}

	@Override
	List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay, boolean canLeadAss) {

		if (canLeadAss && hasAssInHand(cardsInHand)) return [Ass]

		return allCardsWithValue(cardsInHand, cardsInHand.first()) as List<Card>
	}

	static def hasAssInHand(cardsInHand) {

		cardsInHand.find{ it == Ass }
	}

	static def nonAssCardsInHand(cardsInHand) {

		cardsInHand.findAll{ it != Ass }
	}

	static def allCardsWithValue(cardsInHand, card) {

		cardsInHand.dropWhile{ it != card }.takeWhile{ it == card }
	}

	static def allCardsWithValueUsingJokers(cardsInHand, card) {

		def jokersInHand = allCardsWithValue(cardsInHand, Joker)

		if (card == Joker) {

			return [jokersInHand]
		}

		def numberOfJokers = jokersInHand.size()

		def workingList = allCardsWithValue(cardsInHand, card)

		def possibilities = [workingList]

		numberOfJokers.times{

			workingList += Joker

			possibilities << workingList
		}

		return possibilities
	}

	@Override
	public String toString() {

		"SimpleStrategy"
	}
}
