package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Game;

import java.util.List;

public interface GameRepository {
    Game save(Game game);
    Game findById(String id);
    List<Game> findByPlayerId(String playerId);
    void delete(String id);

    List<Game> findAll();
}
