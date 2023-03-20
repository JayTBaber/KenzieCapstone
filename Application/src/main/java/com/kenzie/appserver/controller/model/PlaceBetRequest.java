package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;


public class PlaceBetRequest {
    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;
    @NotEmpty
    @JsonProperty("betAmount")
    private int betAmount;

    public PlaceBetRequest(String playerId, int betAmount) {
        this.playerId = playerId;
        this.betAmount = betAmount;
    }

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
}
