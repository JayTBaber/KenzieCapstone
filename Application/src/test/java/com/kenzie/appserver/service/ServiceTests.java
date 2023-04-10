package com.kenzie.appserver.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.controller.model.PlayerResponse;
import com.kenzie.appserver.dao.CardDAO;
import com.kenzie.appserver.enums.Rank;
import com.kenzie.appserver.enums.Suit;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.PlayerRepository;
import com.kenzie.appserver.repositories.ScoreRepository;
import com.kenzie.appserver.service.model.Card;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.GameData;
import com.kenzie.capstone.service.model.PlayerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServiceTests {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private LambdaServiceClient lambdaServiceClient;
    @Mock
    private ScoreService scoreService;
    @Mock
    private ScoreRepository scoreRepository;
    @Mock
    private CardDAO cardDAO;
    @InjectMocks
    private CardService cardService;
    @InjectMocks
    private GameService gameService;
    @InjectMocks
    private PlayerService playerService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testStartNewGame() {
        String userId = "1234";
        GameData gameData = new GameData();
        gameData.setGameId("1234");
        gameData.setPlayerId(UUID.randomUUID().toString());

        when(lambdaServiceClient.setGameData(userId)).thenReturn(gameData);

        GameResponse gameResponse = gameService.startNewGame(userId);

        Assertions.assertNotNull(gameResponse);
        assertEquals("1234", gameResponse.getGameId());

        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    public void testCreateNewPlayer() {
        String userId = "Josh";
        PlayerData playerData = new PlayerData();
        playerData.setName("Josh");
        playerData.setPlayerId(UUID.randomUUID().toString());

        when(lambdaServiceClient.setPlayerData(userId)).thenReturn(playerData);

        PlayerResponse playerResponse = playerService.createNewPlayer(userId);

        Assertions.assertNotNull(playerResponse);
        assertEquals("Josh", playerResponse.getName());

        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    public void testFindAllGames() {
        GameData game1 = new GameData();
        game1.setGameId("1234");
        game1.setPlayerId(UUID.randomUUID().toString());

        GameData game2 = new GameData();
        game2.setGameId("5678");
        game2.setPlayerId(UUID.randomUUID().toString());

        List<Game> games = new ArrayList<>();
        games.add(new Game(game1.getGameId()));
        games.add(new Game(game2.getGameId()));
        when(gameRepository.findAll()).thenReturn(games);

        List<GameResponse> gameResponses = gameService.findAllGames();

        Assertions.assertNotNull(gameResponses);
        assertEquals(2, gameResponses.size());
        assertEquals("1234", gameResponses.get(0).getGameId());
        assertEquals("5678", gameResponses.get(1).getGameId());
    }

    @Test
    public void testFindAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Josh", 500));
        players.add(new Player("Alice", 100));

        when(playerRepository.findAll()).thenReturn(players);

        List<PlayerResponse> playerResponses = playerService.findAllPlayers();

        assertEquals(2, playerResponses.size());
        assertEquals("Josh", playerResponses.get(0).getPlayerId());
        assertEquals(500, playerResponses.get(0).getBalance());
        assertEquals("Alice", playerResponses.get(1).getPlayerId());
        assertEquals(100, playerResponses.get(1).getBalance());
    }


    @Test
    public void testDeleteGameById() {
        String gameId = "1234";
        GameData game = new GameData();
        game.setGameId(gameId);
        game.setPlayerId(UUID.randomUUID().toString());

        gameService.deleteGame(game);

        verify(gameRepository, times(1)).delete(String.valueOf(game));
    }

    @Test
    public void testDeletePlayerById() {
        String playerId = UUID.randomUUID().toString();
        PlayerData player = new PlayerData();
        player.setPlayerId(playerId);
        player.setName("John");

        playerService.deletePlayer(player);

        verify(playerRepository, times(1)).delete(String.valueOf(player));
    }

    @Test
    public void testGetAllCards() {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(Suit.HEARTS, Rank.ACE);
        Card card2 = new Card(Suit.CLUBS, Rank.KING);
        cards.add(card1);
        cards.add(card2);
        when(cardDAO.findAll()).thenReturn(cards);

        List<Card> actualCards = cardService.getAllCards();

        assertEquals(cards.size(), actualCards.size());
        Assertions.assertTrue(actualCards.contains(card1));
        Assertions.assertTrue(actualCards.contains(card2));
    }
    @Test
    public void testSaveCard() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        when(cardDAO.save(card)).thenReturn(card);

        Card actualCard = cardService.saveCard(card);

        assertEquals(card, actualCard);
    }
    @Test
    public void testDeleteCard() {
        Card card = new Card(Suit.DIAMONDS, Rank.TEN);

        cardService.deleteCard(card);

        verify(cardDAO).delete(card);
    }
}