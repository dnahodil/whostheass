package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.player.OpponentView

import static com.fgi.whostheass.cards.Card.*

class TerribleStrategy implements PlayStrategy {
	@Override
	List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {
		return [] // Pass
	}

	@Override
	List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay) {
		return cardsInHand.last() == Joker ? [Joker, Joker] : [cardsInHand.last()] // Play highest value card or Joker as Joker(14)
	}

	@Override
	List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay, boolean canLeadAss) {
		return [cardsInHand.first()] // Play weakest card
	}

	@Override
	void updateAfterNormalRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView winner) {
		// Do nothing
	}

	@Override
	void updateAfterAssRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView tookCards) {
		// Do nothing
	}

	@Override
	public String toString() {
		return "TerribleStrategy"
	}
}
