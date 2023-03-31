package com.kenzie.appserver.controller.model;

public class ScoreResponse {

    private String id;
    private String playerId;
    private String gameId;
    private int score;

    public ScoreResponse() {}

    public ScoreResponse(String id, String playerId, String gameId, int score) {
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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