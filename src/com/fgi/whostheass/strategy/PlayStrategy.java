package com.fgi.whostheass.strategy;

import com.fgi.whostheass.cards.Card;
import com.fgi.whostheass.player.OpponentView;

import java.util.List;

public interface PlayStrategy {

	public List<Card> startRound(List<Card> cardsInHand, List<OpponentView> playersStillToPlay, boolean canLeadAss);

	public List<Card> playNormalRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay);

	public List<Card> playAssRound(List<Card> cardsInHand, List<OpponentView> playersWhoHavePlayed, List<List<Card>> movesPlayed, List<OpponentView> playersStillToPlay);

	public void updateAfterNormalRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView winner);

	public void updateAfterAssRound(List<OpponentView> allPlayers, List<List<Card>> movesPlayed, OpponentView tookCards);
}