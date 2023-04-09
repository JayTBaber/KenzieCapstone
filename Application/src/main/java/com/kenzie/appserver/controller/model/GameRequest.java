package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class GameRequest {

//    @NotEmpty
//    @JsonProperty("gameId")
//    private String gameId;

    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    @Min(0)
    @JsonProperty("score")
    private int score;

    public GameRequest(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

//    public String getGameId() {
//        return gameId;
//    }

    //public void setGameId(String gameId) { this.gameId = gameId; }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) { this.playerId = playerId; }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
