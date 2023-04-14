package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public class DynamoDBGameRepository {

    private final DynamoDBMapper mapper;



    public DynamoDBGameRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;

    }

    public Game save(Game game) {
        mapper.save(game);
        return game;
    }

    public Game findById(String id) {
        return mapper.load(Game.class, id);
    }


    public Game findByPlayerId(String playerId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("playerId", new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue(playerId)));
        PaginatedScanList<Game> scanResult = mapper.scan(Game.class, scanExpression);
        if (scanResult.isEmpty()) {
            return null;
        }
        return scanResult.get(0);
    }


    public void delete(String id) {

    }


    public List<Game> findAll() {
        return null;
    }
}