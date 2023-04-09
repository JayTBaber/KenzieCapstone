package com.kenzie.appserver.controller.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerResponse {

    @JsonProperty("playerId")
    private String playerId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;


    public PlayerResponse(String playerId, String name, String email) {
        this.playerId = playerId;
        this.name = name;
        this.email = email;
    }

    public PlayerResponse() {
    }

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