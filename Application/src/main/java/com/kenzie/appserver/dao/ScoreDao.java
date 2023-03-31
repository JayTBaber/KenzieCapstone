package com.kenzie.appserver.dao;

import com.kenzie.appserver.repositories.model.ScoreRecord;

import java.util.List;

public interface ScoreDao {

    ScoreRecord save(ScoreRecord score);

    ScoreRecord findById(String id);

    List<ScoreRecord> findAll();

    List<ScoreRecord> findByPlayerId(String playerId);

    void delete(String id);

}

