package com.kenzie.appserver.service.model;

import com.kenzie.appserver.enums.Rank;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final String name;
    private final List<Card> hand;
    private boolean isBusted;

    public Dealer() {
        this.name = "Dealer";
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void dealCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getTotalPoints() {
        int totalPoints = 0;
        int numAces = 0;

        for (Card card : hand) {
            totalPoints += card.getRank().getValue();
            if (card.getRank() == Rank.ACE) {
                numAces++;
            }
        }

        while (numAces > 0 && totalPoints <= 11) {
            totalPoints += 10;
            numAces--;
        }

        return totalPoints;
    }

    public void setBusted() {
        this.isBusted = true;
    }

    public boolean isBusted() {
        return this.isBusted;
    }
}
