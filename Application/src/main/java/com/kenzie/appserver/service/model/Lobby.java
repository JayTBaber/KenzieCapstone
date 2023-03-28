//package com.kenzie.appserver.service.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Lobby {
//    private List<Game> games;
//
//    public Lobby() {
//        games = new ArrayList<Game>();
//    }
//
//    public Game createGame(String gameId, List<String> playerIds) {
//        Game game = new Game(gameId, playerIds);
//        games.add(game);
//        return game;
//    }
//
//    public List<Game> getAvailableGames() {
//        List<Game> availableGames = new ArrayList<Game>();
//        for (Game game : games) {
//            if (!game.isFull()) {
//                availableGames.add(game);
//            }
//        }
//        return availableGames;
//    }
//}
