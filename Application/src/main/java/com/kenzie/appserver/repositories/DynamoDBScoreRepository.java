package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.kenzie.appserver.service.model.Score;

import java.util.List;

public class DynamoDBScoreRepository implements ScoreRepository {

    private final DynamoDBMapper mapper;

    public DynamoDBScoreRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Score score) {
        mapper.save(score);
    }

    @Override
    public Score findById(String id) {
        return mapper.load(Score.class, id);
    }

    @Override
    public List<Score> findByPlayerId(String playerId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("playerId", new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue(playerId)));
        return mapper.scan(Score.class, scanExpression);
    }

    @Override
    public void delete(String id) {
        Score score = new Score();
        score.setId(id);
        mapper.delete(score);
    }
}