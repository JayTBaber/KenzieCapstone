package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Games")
public class GameRecord {

    private String gameId;


    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }


    public void setGameId(String id) {
        this.gameId = gameId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameRecord gameRecord = (GameRecord) o;
        return Objects.equals(gameId, gameRecord.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }
}