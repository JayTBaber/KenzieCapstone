package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class GameUpdateResponse {

    @NotEmpty
    @JsonProperty("gameId")
    private String gameId;

    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    @Min(0)
    @JsonProperty("score")
    private int score;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;


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
}
