package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.repositories.DynamoDBGameRepository;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.model.GameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {
    private AmazonDynamoDB amazonDynamoDB;
    private GameRepository gameRepository;
    private LambdaServiceClient lambdaServiceClient;

    @Autowired
    public GameService(GameRepository gameRepository, LambdaServiceClient lambdaServiceClient) {
        this.gameRepository = gameRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public GameService(DynamoDBGameRepository gameRepository) {
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


    @Cacheable("myCache")
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> getGameById(long id) {
        return Optional.ofNullable(gameRepository.findById(String.valueOf(id)));
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @CacheEvict(value = "myCache", allEntries=true)
    public void deleteGame(GameData game) {
        gameRepository.delete(game.toString());
    }

    public Game createGame(List<Player> playerNames) {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game(gameId, playerNames);
        gameRepository.save(game);
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        mapper.save(game);
        return game;
    }

    public Game createGame(String gameId) {
        Game game = new Game(gameId);
        gameRepository.save(game);
        return game;
    }

    public GameResponse startNewGame(String userId) {
        GameData dataFromLambda = lambdaServiceClient.setGameData(userId);
        GameResponse game = new GameResponse();
        game.setGameId(dataFromLambda.getGameId());
        game.setPlayerId(dataFromLambda.getPlayerId());
        Game newGame = createGame(game.getGameId());
        return game;
    }

    public List<GameResponse> findAllGames() {
        List<Game> games = gameRepository.findAll();
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Game game : games) {
            GameResponse gameResponse = new GameResponse();
            gameResponse.setGameId(game.getGameId());
            gameResponses.add(gameResponse);
        }
        return gameResponses;
    }


//    public GameResponse findGameById(String gameId) {
//        Game game = gameRepository.findById(gameId)
//                .orElseThrow(() -> new NotFoundException("Game not found with id: " + gameId));
//
//        GameResponse gameResponse = new GameResponse();
//        gameResponse.setGameId(game.getGameId());
//        gameResponse.setPlayerId(game.getPlayerId());
//
//        return gameResponse;
//    }

}
