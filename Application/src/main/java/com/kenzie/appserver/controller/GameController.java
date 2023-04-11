package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

//    @GetMapping("/{gameId}")
//    public ResponseEntity<Optional<Game>> getGameById(@PathVariable int gameId) {
//        Optional<Game> game = gameService.getGameById(gameId);
//        if (game.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(game, HttpStatus.OK);
//        }
//    }

//    @PostMapping
//    public ResponseEntity<Game> createGame(@RequestBody List<Player> playerNames) {
//        Game game = gameService.createGame(playerNames);
//        return new ResponseEntity<>(game, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/place-bet")
//    public ResponseEntity<PlaceBetResponse> placeBet(@RequestBody PlaceBetRequest request) {
//        boolean betPlaced = gameService.placeBet(request.getPlayerId(), request.getBetAmount());
//        String message = betPlaced ? "Bet placed successfully." : "Bet placement failed.";
//        PlaceBetResponse response = new PlaceBetResponse(betPlaced, message);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/hit")
//    public ResponseEntity<HitResponse> hit(@RequestBody HitRequest request) {
//        Card cardDealt = gameService.hit(request.getPlayerId());
//        String message = "Card dealt: " + cardDealt.toString();
//        HitResponse response = new HitResponse(cardDealt, message);
//        return ResponseEntity.ok(response);
//    }

//    @PostMapping("/stand")
//    public ResponseEntity<StandResponse> stand(@RequestBody StandRequest request) {
//        gameService.stand(request.getPlayerId());
//        String message = "Player stood.";
//        StandResponse response = new StandResponse(message);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        String gameId = UUID.randomUUID().toString();
        game.getGameId();
        gameService.createGame(gameId);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<GameResponse> startNewGame(@RequestBody GameRequest gameRequest) {
        GameResponse response = gameService.startNewGame(gameRequest.getPlayerId());
        return ResponseEntity.ok(response);
    }

}
