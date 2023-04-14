package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.model.ExampleRecord;
import com.kenzie.capstone.service.model.GameData;
import com.kenzie.capstone.service.model.GameRecord;

import java.util.List;

public class GameDao {
    private DynamoDBMapper mapper;
    private GameRecord gameRecord;


    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public GameDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public GameData storeGameData(GameData gameData) {
        gameRecord = new GameRecord();
        gameRecord.setGameId(gameData.getGameId());

        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression.setExpected(ImmutableMap.of(
                "gameId", new ExpectedAttributeValue(
                        new AttributeValue().withS(gameData.getGameId())
                ).withExists(false)
        ));

        try {
            mapper.save(gameRecord, saveExpression);
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("gameId already exists");
        }

        return gameData;
    }


    public List<GameRecord> getGameData(String id) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setGameId(id);

        DynamoDBQueryExpression<GameRecord> queryExpression = new DynamoDBQueryExpression<GameRecord>()
                .withHashKeyValues(gameRecord)
                .withConsistentRead(false);

        return mapper.query(GameRecord.class, queryExpression);
    }

    public GameRecord setGameData(String id, String data) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setGameId(id);
        gameRecord.setPlayerId(data);

        try {
            mapper.save(gameRecord, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "gameId",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("gameId already exists");
        }

        return gameRecord;
    }
}
