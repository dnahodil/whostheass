package com.fgi.whostheass.cards

class Deck {

    static final def numberOfJokers = 5
    static final def numberOfEachNumber = 8

    def cards = []

    void deal(players) {

        // Insert Ass

        cards.each{ println it }
    }

    static def shuffledDeck() {

        def deck = new Deck()

        deck.cards.addAll jokers
        deck.cards.addAll numberCards

        Collections.shuffle deck.cards

        return deck
    }

    static def getTheAss() {

        new Card(CardValue.Ass)
    }

    static def getJokers() {

        def jokers = []

        numberOfJokers.times{ jokers << new Card(CardValue.Joker) }

        return jokers
    }

    static def getNumberCards() {

        def numbers = []

        CardValue.numberCards.each {
            cardValue ->

            numberOfEachNumber.times {

                numbers << new Card(cardValue)
            }
        }

        return numbers
    }
}
