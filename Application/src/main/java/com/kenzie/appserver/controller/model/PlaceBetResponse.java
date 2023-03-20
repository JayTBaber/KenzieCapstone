package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceBetResponse {

    @JsonProperty("betPlaced")
    private boolean betPlaced;

    @JsonProperty("message")
    private String message;

    public PlaceBetResponse(boolean betPlaced, String message) {
        this.betPlaced = betPlaced;
        this.message = message;
    }

    public boolean isBetPlaced() {
        return betPlaced;
    }

    public void setBetPlaced(boolean betPlaced) {
        this.betPlaced = betPlaced;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
