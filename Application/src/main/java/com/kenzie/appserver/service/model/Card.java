package com.kenzie.appserver.service.model;

import com.kenzie.appserver.enums.Rank;
import com.kenzie.appserver.enums.Suit;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public long getId() {
        return rank.ordinal();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

