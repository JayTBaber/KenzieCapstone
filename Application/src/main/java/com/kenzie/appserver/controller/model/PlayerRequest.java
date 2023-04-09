package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class PlayerRequest {

    @NotEmpty
    @JsonProperty("playerId")
    private Optional<String> playerId;

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;


    public PlayerRequest() {
    }

    public Optional<String> getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Optional<String> playerId) {
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
