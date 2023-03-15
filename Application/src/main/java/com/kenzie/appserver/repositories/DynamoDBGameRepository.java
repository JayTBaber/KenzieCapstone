package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Game;

import java.util.List;
import java.util.Optional;

public class DynamoDBGameRepository implements GameRepository {

    private final DynamoDBMapper mapper;

    GameService gameService = new GameService();

    public DynamoDBGameRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Game save(Game game) {
        mapper.save(game);
        return game;
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
        Optional<Game> game = gameService.getGameById(Long.parseLong(id));
        mapper.delete(game);
    }

    @Override
    public List<Game> findAll() {
        return gameService.getAllGames();
    }
}
