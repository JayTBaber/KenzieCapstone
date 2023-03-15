package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.kenzie.appserver.service.model.Game;

import java.util.List;

public class DynamoDBGameRepository implements GameRepository {

    private final DynamoDBMapper mapper;

    public DynamoDBGameRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Game game) {
        mapper.save(game);
    }

    @Override
    public Game findById(String id) {
        return mapper.load(Game.class, id);
    }

    @Override
    public List<Game> findByPlayerId(String playerId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("playerId", new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue(playerId)));
        return mapper.scan(Game.class, scanExpression);
    }

    @Override
    public void delete(String id) {
        Game game = new Game();
        game.setId(id);
        mapper.delete(game);
    }
}
