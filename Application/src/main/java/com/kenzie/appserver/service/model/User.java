package com.kenzie.appserver.service.model;

import java.util.UUID;

public class User {

    private UUID userId;
    private String username;

    private String password;

    private int purse;

    private int wins;

    private int losses;


    public User(UUID userId, String username, String password, int purse, int wins, int losses) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.purse = purse;
        this.wins = wins;
        this.losses = losses;
    }


    public UUID generateUserID(){
        return userId = UUID.randomUUID();
    }





//TODO- finish user account creation
    public void createUsername (String customUsername){
        username = customUsername;
    }

    public void createPassword(String customPassword){
        password = customPassword;
    }









    public UUID getUserId() {return userId;}
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


    public void setUserId(UUID userId) {this.userId = userId;}
    public void setUserName(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPurse(int purse){
        this.purse = purse;
    }
    public void setWins(int wins){
        this.wins = wins;
    }
    public void setLosses(int losses){
        this.losses = losses;
    }
}
