package com.kenzie.appserver.service.model;

import com.kenzie.appserver.controller.model.HitResponse;
import com.kenzie.appserver.controller.model.JoinGameResponse;
import com.kenzie.appserver.controller.model.PlaceBetResponse;
import com.kenzie.appserver.controller.model.StandResponse;

import java.util.List;

public class Room {
    private Game game;

    public Room(String gameId, List<String> playerIds) {
        game = new Game(gameId, playerIds);
    }

    public GameState getGameState() {
        return game.getState();
    }

    public HitResponse processHitRequest(String playerId) {
        return game.processHitRequest(playerId);
    }

    public JoinGameResponse processJoinGameRequest(String playerId) {
        return game.processJoinGameRequest(playerId);
    }

    public PlaceBetResponse processPlaceBetRequest(String playerId, int betAmount) {
        return game.processPlaceBetRequest(playerId, betAmount);
    }

    public StandResponse processStandRequest(String playerId) {
        return game.processStandRequest(playerId);
    }
}

