package com.kenzie.appserver.controller.model;

import java.time.LocalDateTime;

public class GameResponse {
    private String id;
    private String playerId;
    private int score;
    private LocalDateTime timestamp;

    public GameResponse(String id, String playerId, int score, LocalDateTime timestamp) {
        this.id = id;
        this.playerId = playerId;
        this.score = score;
        this.timestamp = timestamp;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
