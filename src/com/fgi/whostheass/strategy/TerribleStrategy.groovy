package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.player.OpponentView

class TerribleStrategy implements PlayStrategy {

	@Override
	List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhovePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		return [] // Pass
	}

	@Override
	List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhovePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {

		return [cardsInHand.last()] // Play highest value card
	}

	@Override
	List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay, boolean canLeadAss) {

		return [cardsInHand.first()] // Play weakest card
	}

	@Override
	public String toString() {

		return "TerribleStrategy"
	}
}
