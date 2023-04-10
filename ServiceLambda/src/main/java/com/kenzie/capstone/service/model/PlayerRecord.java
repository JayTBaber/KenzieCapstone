package com.kenzie.capstone.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "Players")
public class PlayerRecord {
    private String playerId;
    private String name;
    private String email;
    private int balance;

    @DynamoDBHashKey(attributeName = "playerId")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "BalanceIndex")
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "NameIndex", attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "BalanceIndex", attributeName = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
