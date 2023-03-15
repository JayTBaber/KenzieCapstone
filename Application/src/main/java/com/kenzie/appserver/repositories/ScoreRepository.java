package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Score;

import java.util.List;

public interface ScoreRepository {
    void save(Score score);
    Score findById(String id);
    List<Score> findByPlayerId(String playerId);
    void delete(String id);
}
