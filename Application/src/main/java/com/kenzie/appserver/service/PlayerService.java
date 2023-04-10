package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.controller.model.PlayerResponse;
import com.kenzie.appserver.repositories.DynamoDBPlayerRepository;
import com.kenzie.appserver.repositories.PlayerRepository;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.GameData;
import com.kenzie.capstone.service.model.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private LambdaServiceClient lambdaServiceClient;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, LambdaServiceClient lambdaServiceClient) {
        this.playerRepository = playerRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public PlayerService(DynamoDBPlayerRepository playerRepository){}

    @Cacheable("myCache")
    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player getPlayerById(String id) {
        return playerRepository.findById(id);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @CacheEvict(value = "myCache", allEntries=true)
    public void deletePlayer(PlayerData player) {
        playerRepository.delete(String.valueOf(player));
    }

    public Player createPlayer(String playerId) {
        Player player = new Player(playerId);
        playerRepository.save(player);
        return player;
    }

    public PlayerResponse createNewPlayer(String userId) {
        PlayerData dataFromLambda = lambdaServiceClient.setPlayerData(userId);
        PlayerResponse player = new PlayerResponse();
        player.setPlayerId(dataFromLambda.getPlayerId());
        player.setName(dataFromLambda.getName());
        Player newPlayer = createPlayer(player.getPlayerId());
        return player;
    }

    public List<PlayerResponse> findAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerResponse> playerResponses = new ArrayList<>();
        for (Player player : players) {
            PlayerResponse playerResponse = new PlayerResponse();
            playerResponse.setPlayerId(player.getPlayerId());
            playerResponse.setName(player.getName());
            playerResponse.setBalance(player.getBalance());
            playerResponses.add(playerResponse);
        }
        return playerResponses;
    }

//    public PlayerResponse findPlayerById(String playerId) {
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new NotFoundException("Player not found with id: " + playerId));
//
//        PlayerResponse playerResponse = new PlayerResponse();
//        playerResponse.setId(player.getId());
//        playerResponse.setName(player.getName());
//
//        return playerResponse;
//    }

}
