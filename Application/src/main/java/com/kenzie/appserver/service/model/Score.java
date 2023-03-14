package com.kenzie.appserver.service.model;


public class Score {
    private long id;

    private long playerId;

    private int scoreValue;

    private Card card;

    public Score() {}

    public Score(long playerId, int scoreValue) {
        this.playerId = playerId;
        this.scoreValue = scoreValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }

}
