package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.controller.model.PlayerRequest;
import com.kenzie.appserver.controller.model.PlayerResponse;
import com.kenzie.appserver.service.PlayerService;
import com.kenzie.appserver.service.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int playerId) {
        Player player = playerService.getPlayerById(String.valueOf(playerId));
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerRequest playerRequest) {
        PlayerResponse response = playerService.createNewPlayer(playerRequest.getPlayerId());
        return ResponseEntity.ok(response);
    }
}
