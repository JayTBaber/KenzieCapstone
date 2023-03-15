package com.kenzie.appserver.repositories;

import com.kenzie.appserver.service.model.Player;

public interface PlayerRepository {
    void save(Player player);
    Player findById(String id);
    void delete(String id);
}

