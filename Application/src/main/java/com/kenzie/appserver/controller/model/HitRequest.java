package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;


public class HitRequest {
    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    public HitRequest(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
