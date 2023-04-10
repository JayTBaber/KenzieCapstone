package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerId;
    private String name;
    private List<Card> hand;
    private int totalPoints;
    private boolean standing;
    private int balance;
    private int betAmount;

    public Player(String playerId, int balance) {
        this.playerId = playerId;
        this.hand = new ArrayList<>();
        this.totalPoints = 0;
        this.standing = false;
        this.balance = balance;
        this.betAmount = 0;
    }


    public String getName() {
        return name;
    }

    public void dealCard(Card card) {
        hand.add(card);
        totalPoints += card.getRank().getValue();

        if (totalPoints > 21) {
            setBusted();
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public boolean shouldHit() {
        return !standing && totalPoints < 21;
    }

    public void setStanding() {
        standing = true;
    }

    public boolean isStanding() {
        return standing;
    }

    public void setBusted() {
        standing = true;
    }

    public boolean isBusted() {
        return standing && totalPoints > 21;
    }

    public void win() {
        balance += betAmount;
        System.out.println(name + " wins!");
    }

    public void lose() {
        balance -= betAmount;
        System.out.println(name + " loses.");
    }

    public void tie() {
        System.out.println(name + " ties with the dealer.");
    }

    public int getBalance() {
        return balance;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public void withdraw(int betAmount) {
        balance -= betAmount;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
