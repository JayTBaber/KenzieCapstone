package com.kenzie.appserver.dao;

import com.kenzie.appserver.service.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private final List<Player> players;

    public PlayerDAO() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
