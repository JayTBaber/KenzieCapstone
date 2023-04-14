package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository {
    Game save(Game game);
    Game findById(String id);
    Game findByPlayerId(String playerId);
    void delete(String id);

    List<Game> findAll();
}
