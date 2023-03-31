package com.kenzie.appserver.repositories.model;

import com.kenzie.appserver.service.model.Card;

import java.util.List;

public class PlayerRecord {
    private String playerId;
    private int betAmount;
    private List<Card> cards;
    private int totalPoints;
    private boolean isBusted;
    private boolean isStanding;
    private String result;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public boolean isStanding() {
        return isStanding;
    }

    public void setStanding(boolean standing) {
        isStanding = standing;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
