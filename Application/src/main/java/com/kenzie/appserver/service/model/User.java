package com.kenzie.appserver.service.model;

public class User {

    private String userName;

    private String password;

    private int purse;

    private int wins;

    private int losses;


    public User(String userName, String password, int purse, int wins, int losses) {
        this.userName = userName;
        this.password = password;
        this.purse = purse;
        this.wins = wins;
        this.losses = losses;
    }

    public String getUserName() {
        return userName;
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

    public void setUserName(String userName){
        this.userName = userName;
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
