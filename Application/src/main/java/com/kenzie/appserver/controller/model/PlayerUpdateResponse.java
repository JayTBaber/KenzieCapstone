package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class PlayerUpdateResponse {

    @NotEmpty
    @JsonProperty("playerId")
    private String playerId;

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @NotEmpty
    @JsonProperty("email")
    private String email;



    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String id) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
