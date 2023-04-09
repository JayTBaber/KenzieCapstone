package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.GameDao;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.ExampleRecord;
import com.kenzie.capstone.service.model.GameData;
import com.kenzie.capstone.service.model.GameRecord;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;

public class LambdaService {

    private ExampleDao exampleDao;

    private GameDao gameDao;

    @Inject
    public LambdaService(ExampleDao exampleDao, GameDao gameDao) {
        this.exampleDao = exampleDao;
        this.gameDao = gameDao;
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
}