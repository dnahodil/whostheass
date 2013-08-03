package com.fgi.whostheass.cards

import static com.fgi.whostheass.game.Rules.*

class Card {

    def value

    def Card() {
    }

    @Override
    public String toString() {

        return value
    }

    static def getTheAss() {

        new Card(value: CardValue.Ass)
    }

    static def getJokers() {

        def jokers = []

        numberOfJokersInDeck.times{
            jokers << new Card(value: CardValue.Joker)
        }

        return jokers
    }

    static def getNumberCards() {

        def numbers = []

        CardValue.numberCards.each {
            cardValue ->

                numberOfEachNumberInDeck.times {

                    numbers << new Card(value: cardValue)
                }
        }

        return numbers
    }
}
