package com.kenzie.capstone.service.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GameData {
    private String gameId;
    private String playerId;

    private int score;

    private LocalDateTime timestamp;


    public GameData(String gameId, String playerId, int score, LocalDateTime timestamp) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.score =  score;
        this.timestamp = timestamp;
    }

    public GameData() {}

    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameData gameData = (GameData) o;
        return Objects.equals(gameId, gameData.gameId) && Objects.equals(playerId, gameData.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}