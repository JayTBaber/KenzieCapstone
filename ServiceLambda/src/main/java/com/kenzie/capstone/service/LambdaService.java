package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.GameDao;
import com.kenzie.capstone.service.dao.PlayerDao;
import com.kenzie.capstone.service.model.*;
import com.kenzie.capstone.service.dao.ExampleDao;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;

public class LambdaService {

    private ExampleDao exampleDao;

    private GameDao gameDao;

    private PlayerDao playerDao;

    @Inject
    public LambdaService(ExampleDao exampleDao, GameDao gameDao, PlayerDao playerDao) {
        this.exampleDao = exampleDao;
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public ExampleData getExampleData(String id) {
        List<ExampleRecord> records = exampleDao.getExampleData(id);
        if (records.size() > 0) {
            return new ExampleData(records.get(0).getId(), records.get(0).getData());
        }
        return null;
    }

    public ExampleData setExampleData(String data) {
        String id = UUID.randomUUID().toString();
        ExampleRecord record = exampleDao.setExampleData(id, data);
        return new ExampleData(id, data);
    }

    public GameData getGameData(String id) {
        List<GameRecord> records = gameDao.getGameData(id);
        if (records.size() > 0) {
            return new GameData(records.get(0).getGameId(), records.get(0).getPlayerId(),0, null);
        }
        return null;
    }

    public GameData setGameData(String data) {
        String id = UUID.randomUUID().toString();
        ExampleRecord record = exampleDao.setExampleData(id, data);
        return new GameData(id, data, 0, null);
    }

    public PlayerData getPlayerData(String id) {
        List<PlayerRecord> records = playerDao.getPlayerData(id);
        if (records.size() > 0) {
            return new PlayerData(records.get(0).getPlayerId(), records.get(0).getName(), records.get(0).getEmail(), records.get(0).getBalance());
        }
        return null;
    }

    public PlayerData setPlayerData(String data) {
        String id = UUID.randomUUID().toString();
        PlayerRecord record = playerDao.setPlayerData(id, data);
        return new PlayerData(id, data, null, 0);
    }
}