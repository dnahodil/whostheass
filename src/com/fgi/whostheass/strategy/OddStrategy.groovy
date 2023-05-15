package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.player.OpponentView

import static com.fgi.whostheass.cards.Card.*

class OddStrategy implements PlayStrategy {
	@Override
	List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {
		return []
	}

	@Override
	List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {
		if (cardsInHand.find{ it == Joker }) {
			return [Joker, Thirteen]
		}

		return [cardsInHand.first()] // Play lowest card
	}

	@Override
	List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay, boolean canLeadAss) {
		if (canLeadAss && hasAssInHand(cardsInHand)) return [Ass]

		return allCardsWithValue(cardsInHand, cardsInHand.first()) as List<Card>
	}

	@Override
	void updateAfterNormalRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView winner) {
		// Do nothing
	}

	@Override
	void updateAfterAssRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView tookCards) {
		// Do nothing
	}

	static def hasAssInHand(cardsInHand) {
		cardsInHand.find{ it == Ass }
	}

	static def allCardsWithValue(cardsInHand, card) {
		cardsInHand.dropWhile{ it != card }.takeWhile{ it == card }
	}

	@Override
	public String toString() {
		"OddStrategy"
	}
}
