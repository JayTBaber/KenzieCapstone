package com.kenzie.appserver.dao;

import com.kenzie.appserver.service.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private final List<Game> games;

    public GameDAO() {
        this.games = new ArrayList<>();
    }

    public void saveGame(Game game) {
        games.add(game);
    }

    public List<Game> getAllGames() {
        return games;
    }

//    public Game getGameById() {
//    }
}
