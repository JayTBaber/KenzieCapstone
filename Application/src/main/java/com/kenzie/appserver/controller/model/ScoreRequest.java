package com.kenzie.appserver.controller.model;

public class ScoreRequest {

    private String playerId;
    private String gameId;
    private int score;

    public ScoreRequest() {}

    public ScoreRequest(String playerId, String gameId, int score) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.score = score;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}