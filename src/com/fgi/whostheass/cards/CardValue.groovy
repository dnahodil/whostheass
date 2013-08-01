package com.fgi.whostheass.cards

public enum CardValue {

    Ass(20),
    Joker(14),

    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Eleven(11),
    Twelve(12),
    Thirteen(13)

    static numberCards = [One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven, Twelve, Thirteen]

    def value

    CardValue(v) {

        value = v
    }
}