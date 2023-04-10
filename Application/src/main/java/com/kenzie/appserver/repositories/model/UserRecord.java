package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;


@DynamoDBTable(tableName = "Users")
public class UserRecord {

    private String username;

    private String password;

    private Integer purse;

    private Integer wins;

    private Integer losses;


    @DynamoDBHashKey(attributeName = "username")
    public String getUsername(){return username;}

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword(){return password;}

    @DynamoDBAttribute(attributeName = "purse")
    public Integer getPurse(){return purse;}

    @DynamoDBAttribute(attributeName = "wins")
    public Integer getWins(){return wins;}

    @DynamoDBAttribute(attributeName = "losses")
    public Integer getLosses(){return losses;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPurse(Integer purse) {
        this.purse = purse;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecord userRecord = (UserRecord) o;
        return Objects.equals(username, userRecord.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
