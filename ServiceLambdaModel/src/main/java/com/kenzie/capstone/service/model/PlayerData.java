package com.kenzie.capstone.service.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PlayerData {
    private String playerId;
    private String name;
    private String email;
    private int balance;


    public PlayerData(String playerId, String name, String email, int balance) {
        this.playerId = playerId;
        this.name = name;
        this.email =  email;
        this.balance = balance;
    }

    public PlayerData() {}

    public String getPlayerId() {
        return playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerData playerData = (PlayerData) o;
        return Objects.equals(playerId, playerData.playerId) && Objects.equals(playerId, balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, balance);
    }
}