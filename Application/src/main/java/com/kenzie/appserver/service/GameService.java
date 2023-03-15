package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> getGameById(long id) {
        return Optional.ofNullable(gameRepository.findById(String.valueOf(id)));
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        gameRepository.delete(game.toString());
    }

    public Game createGame(List<Player> playerNames) {
        Game game = new Game(playerNames);
        gameRepository.save(game);
        return game;
    }
}
