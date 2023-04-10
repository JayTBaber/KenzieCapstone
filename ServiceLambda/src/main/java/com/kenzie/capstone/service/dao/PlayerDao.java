package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.PlayerData;
import com.kenzie.capstone.service.model.PlayerRecord;

import java.util.List;

public class PlayerDao {
    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public PlayerDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public PlayerData storeGameData(PlayerData playerData) {
        try {
            mapper.save(playerData, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "playerId",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("gameId has already been used");
        }

        return playerData;
    }

    public List<PlayerRecord> getPlayerData(String id) {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.setPlayerId(id);

        DynamoDBQueryExpression<PlayerRecord> queryExpression = new DynamoDBQueryExpression<PlayerRecord>()
                .withHashKeyValues(playerRecord)
                .withConsistentRead(false);

        return mapper.query(PlayerRecord.class, queryExpression);
    }

    public PlayerRecord setPlayerData(String id, String data) {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.setPlayerId(id);
        playerRecord.setEmail(data);

        try {
            mapper.save(playerRecord, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "gameId",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("gameId already exists");
        }

        return playerRecord;
    }
}
