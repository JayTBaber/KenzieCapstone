package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Player;

import java.util.List;

public interface PlayerRepository {
    Player save(Player player);
    Player findById(String id);
    void delete(String id);

    List<Player> findAll();
}

