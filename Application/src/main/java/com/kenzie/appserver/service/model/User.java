package com.kenzie.appserver.service.model;

import java.util.UUID;

public class User {

    private String username;

    private String password;

    private int purse;

    private int wins;

    private int losses;


    public User(String username, String password, int purse, int wins, int losses) {
        this.username = username;
        this.password = password;
        this.purse = purse;
        this.wins = wins;
        this.losses = losses;
    }


    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPurse() {
        return purse;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }


    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int userWinLossRatio(User user) {
        int wins = this.wins;
        int losses = this.losses;

        for (int i = 0; i < user.wins; i++) {
            wins++;
        }
        for (int j = 0; j < user.losses; j++) {
            losses++;
        }

        int winLossRatio = this.wins / this.losses;

        return winLossRatio;
    }
}

