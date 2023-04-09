package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.PlayerRepository;
import com.kenzie.appserver.service.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {


    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Cacheable("myCache")
    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(long id) {
        return Optional.ofNullable(playerRepository.findById(String.valueOf(id)));
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @CacheEvict(value = "myCache", allEntries=true)
    public void deletePlayer(Player player) {
        playerRepository.delete(String.valueOf(player));
    }

    public Player createPlayer(Player player) {
        String playerId = UUID.randomUUID().toString();
        player.setPlayerId(playerId);
        return playerRepository.save(player);
    }

}
