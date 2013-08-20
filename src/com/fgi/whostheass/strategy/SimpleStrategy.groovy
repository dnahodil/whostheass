package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.player.OpponentView
import static com.fgi.whostheass.cards.CardImpl.*

class SimpleStrategy implements PlayStrategy {

	@Override
	List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		def moveToBeatCards = movesPlayed.findAll{ it.size() }.last()
		def moveToBeatCardPoints = moveToBeatCards.first().points
		def moveToBeatNumberCards = moveToBeatCards.size()

		def cardsHigherThanThosePlayed = cardsInHand.unique().dropWhile{ it.points <= moveToBeatCardPoints }

		def possiblePlays = []

		cardsHigherThanThosePlayed.each{
			card ->
			allCardsWithValueUsingJokers(cardsInHand, card).each{

				if (it.size() == moveToBeatNumberCards)
					possiblePlays << it
			}
		}

		possiblePlays << [] // Can always Pass

		return possiblePlays.first() as List<Card>
	}

	@Override
	List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		return [cardsInHand.first()] // Play lowest card
	}

	@Override
	List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay) {

		if (cardsInHand.find{ it == Ass }) return [Ass]

		return allCardsWithValue(cardsInHand, cardsInHand.first()) as List<Card>
	}

	static def allCardsWithValue(cardsInHand, card) {

		cardsInHand.dropWhile{ it != card }.takeWhile{ it == card }
	}

	static def allCardsWithValueUsingJokers(cardsInHand, card) {

		def numberOfJokers = allCardsWithValue(cardsInHand, Joker).size()

		if (card == Joker) numberOfJokers -= 1

		def workingList = allCardsWithValue(cardsInHand, card)

		def possibilities = [workingList]

		numberOfJokers.times{

			workingList += Joker

			possibilities << workingList
		}

		return possibilities
	}
}
