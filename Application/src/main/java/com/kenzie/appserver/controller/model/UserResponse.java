package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("purse")
    private Integer purse;

    @JsonProperty("wins")
    private Integer wins;

    @JsonProperty("losses")
    private Integer losses;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPurse() {
        return purse;
    }

    public void setPurse(Integer purse) {
        this.purse = purse;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }
}
