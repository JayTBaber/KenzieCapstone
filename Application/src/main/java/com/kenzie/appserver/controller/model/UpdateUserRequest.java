package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class UpdateUserRequest {


    @NotEmpty
    @JsonProperty("username")
    private String username;

    @NotEmpty
    @JsonProperty("purse")
    private String purse;

    @NotEmpty
    @JsonProperty("wins")
    private String wins;

    @NotEmpty
    @JsonProperty("losses")
    private String losses;

    public UpdateUserRequest(String username, String purse, String wins, String losses) {
        this.username = username;
        this.purse = purse;
        this.wins = wins;
        this.losses = losses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPurse() {
        return purse;
    }

    public void setPurse(String purse) {
        this.purse = purse;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }
}
