package com.kenzie.appserver.controller.model;

public class GameRequest {
    private String playerId;
    private int score;

    public GameRequest(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
