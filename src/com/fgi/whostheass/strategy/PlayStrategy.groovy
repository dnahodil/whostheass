package com.fgi.whostheass.strategy

import com.fgi.whostheass.cards.Card
import com.fgi.whostheass.move.Move
import com.fgi.whostheass.player.OpponentView

interface PlayStrategy {

	public Move chooseMove(List<Card> cards, List<OpponentView> playersWhovePlayed, List<Move> movesPlayed, List<OpponentView> playersStillToPlay)
}