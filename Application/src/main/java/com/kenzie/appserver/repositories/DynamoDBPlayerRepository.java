package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.service.model.Player;

public class DynamoDBPlayerRepository implements PlayerRepository {

    private final DynamoDBMapper mapper;

    public DynamoDBPlayerRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Player player) {
        mapper.save(player);
    }

    @Override
    public Player findById(String id) {
        return mapper.load(Player.class, id);
    }

    @Override
    public void delete(String id) {
        Player player = new Player();
        player.setId(id);
        mapper.delete(player);
    }
}
