package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.service.PlayerService;
import com.kenzie.appserver.service.model.Player;

import java.util.List;
import java.util.Optional;

public class DynamoDBPlayerRepository implements PlayerRepository {

    private final DynamoDBMapper mapper;
    PlayerService playerService = new PlayerService();


    public DynamoDBPlayerRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Player save(Player player) {
        mapper.save(player);
        return player;
    }

    @Override
    public Player findById(String id) {
        return mapper.load(Player.class, id);
    }

    @Override
    public void delete(String id) {
        Optional<Player> player = playerService.getPlayerById(Long.parseLong(id));
        mapper.delete(player);
    }

    @Override
    public List<Player> findAll() {
        return playerService.getAllPlayers();
    }
}
