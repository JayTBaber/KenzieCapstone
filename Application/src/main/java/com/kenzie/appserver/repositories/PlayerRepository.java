package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.PlayerRecord;
import com.kenzie.appserver.service.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository {
    Player save(Player player);
    Player findById(String id);
    void delete(String id);

    List<Player> findAll();

    void save(PlayerRecord capture);
}

