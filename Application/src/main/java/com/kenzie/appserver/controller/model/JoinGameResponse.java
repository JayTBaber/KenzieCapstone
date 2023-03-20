package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JoinGameResponse {

    @JsonProperty("playerId")
    private String playerId;

    @JsonProperty("message")
    private String message;

    public JoinGameResponse(String playerId, String message) {
        this.playerId = playerId;
        this.message = message;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
