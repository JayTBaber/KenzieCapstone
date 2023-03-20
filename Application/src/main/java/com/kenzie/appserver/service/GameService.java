package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private GameRepository gameRepository;


    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public boolean placeBet(String playerId, int betAmount) {
        Game game = gameRepository.findByPlayerId(playerId);
        if (game == null || game.isGameOver()) {
            return false;
        }
        boolean betPlaced = game.placeBet(playerId, betAmount);
        if (betPlaced) {
            gameRepository.save(game);
        }
        return betPlaced;
    }

    public Card hit(String playerId) {
        Game game = gameRepository.findByPlayerId(playerId);
        if (game == null || game.isGameOver()) {
            return null;
        }
        Card card = game.dealCard(playerId);
        gameRepository.save(game);
        return card;
    }

    public void stand(String playerId) {
        Game game = gameRepository.findByPlayerId(playerId);
        if (game == null || game.isGameOver()) {
            return;
        }
        game.stand(playerId);
        gameRepository.save(game);
    }


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
