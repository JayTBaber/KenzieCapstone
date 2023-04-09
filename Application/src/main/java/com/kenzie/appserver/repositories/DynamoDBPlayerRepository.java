package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.repositories.model.PlayerRecord;
import com.kenzie.appserver.service.PlayerService;
import com.kenzie.appserver.service.model.Player;

import java.util.List;

public class DynamoDBPlayerRepository implements PlayerRepository {

    private final DynamoDBMapper mapper;
    private final PlayerService playerService;


    public DynamoDBPlayerRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
        this.playerService = new PlayerService(this);
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
        Player player = playerService.getPlayerById(id);
        mapper.delete(player);
    }

    @Override
    public List<Player> findAll() {
        return playerService.getAllPlayers();
    }

    @Override
    public void save(PlayerRecord capture) {

    }
}
