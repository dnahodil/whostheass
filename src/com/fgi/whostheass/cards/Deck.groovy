package com.fgi.whostheass.cards

class Deck {

    static final def numberOfJokersInDeck = 5
    static final def numberOfEachNumberInDeck = 8
    static final def numberOfCardsInHand = 13 // Does this belong here?

    def cards = [] as LinkedList

    def Deck() {

        cards.addAll jokers
        cards.addAll numberCards
        // Only add The Ass once we know how many Players we're dealing to

        Collections.shuffle cards
    }

    void deal(players) {

        def numPlayers = players.size()

        insertAss assLocation(numPlayers)

        numberOfCardsBeingDealt(numPlayers).times {
            cardIndexBeingDealt ->

            def dealTo = cardIndexBeingDealt % numPlayers

            players[dealTo].dealCard(nextCard)
        }

        players.each {

            println "$it has these Cards: ${it.hand.cards}"
        }
    }

    void insertAss(location) {

        cards.add location, theAss
    }

    def assLocation(numPlayers) {

        new Random().nextInt(numberOfCardsBeingDealt(numPlayers))
    }

    def numberOfCardsBeingDealt(numPlayers) {

        numPlayers * numberOfCardsInHand
    }

    def getNextCard() {

        cards.removeFirst()
    }

    static def getTheAss() {

        new Card(CardValue.Ass)
    }

    static def getJokers() {

        def jokers = []

        numberOfJokersInDeck.times{ jokers << new Card(CardValue.Joker) }

        return jokers
    }

    static def getNumberCards() {

        def numbers = []

        CardValue.numberCards.each {
            cardValue ->

            numberOfEachNumberInDeck.times {

                numbers << new Card(cardValue)
            }
        }

        return numbers
    }
}
