package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Game>> getGameById(@PathVariable int id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(game, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody List<Player> playerNames) {
        Game game = gameService.createGame(playerNames);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
}
