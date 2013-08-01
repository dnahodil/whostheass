package com.fgi.whostheass.cards

class Card {

    def value

    def Card(cardValue) {

        value = cardValue
    }

    @Override
    public String toString() {

        return value
    }
}
