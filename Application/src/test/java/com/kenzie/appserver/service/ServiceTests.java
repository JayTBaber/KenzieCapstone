package com.kenzie.appserver.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.controller.model.PlayerResponse;
import com.kenzie.appserver.dao.CardDAO;
import com.kenzie.appserver.enums.Rank;
import com.kenzie.appserver.enums.Suit;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.PlayerRepository;
import com.kenzie.appserver.repositories.ScoreRepository;
import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.model.*;
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
    private UserRepository userRepository;
    @Mock
    private ScoreRepository scoreRepository;
    @InjectMocks
    private ScoreService scoreService;
    @Mock
    private LambdaServiceClient lambdaServiceClient;
    @Mock
    private CardDAO cardDAO;
    @InjectMocks
    private CardService cardService;
    @InjectMocks
    private UserService userService;
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
        GameRecord record1 = new GameRecord();
        record1.setGameId("1234");
        record1.setPlayerId(game1.getPlayerId());


        GameData game2 = new GameData();
        game2.setGameId("5678");
        game2.setPlayerId(UUID.randomUUID().toString());
        GameRecord record2 = new GameRecord();
        record2.setGameId("5678");
        record2.setPlayerId(game2.getPlayerId());

        List<Game> games = new ArrayList<>();
        List<GameRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        Iterable<GameRecord> iterableRecords = records;

        games.add(new Game(game1.getGameId()));
        games.add(new Game(game2.getGameId()));
        when(gameRepository.findAll()).thenReturn(iterableRecords);

        List<Game> gameResponses = gameService.getAllGames();

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

        verify(gameRepository, times(1)).deleteById(gameId);
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
    @Test
    public void testGetCardById() throws EntityNotFoundException {
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        when(cardDAO.findById(1L)).thenReturn(card);

        Card result = cardService.getCardById(1L);

        assertEquals(card, result);
    }


    @Test
    public void testUpdateNonExistingUser() {
        User user = new User("nonExistingUser", "password", 500, 7, 4);

        when(userRepository.existsById("nonExistingUser")).thenReturn(false);

        User result = userService.updateExistingUser(user);

        assertNull(result);

        verify(userRepository, never()).save(any(UserRecord.class));
    }

    @Test
    void testEntityNotFoundException() {
        String message = null;
        EntityNotFoundException exception = new EntityNotFoundException(message);

        assertEquals(null, exception.getMessage());
    }

    @Test
    public void testGetAllScores() {
        Score score1 = new Score(1, 100);
        Score score2 = new Score(2, 200);
        List<Score> scores = Arrays.asList(score1, score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        List<Score> result = scoreService.getAllScores();

        assertEquals(scores, result);

        verify(scoreRepository).findAll();
    }

    @Test
    public void testGetScoreById() {
        Score score = new Score(1, 100);

        when(scoreRepository.findById("1")).thenReturn(score);

        Optional<Score> result = scoreService.getScoreById(1);

        assertEquals(Optional.of(score), result);

        verify(scoreRepository).findById("1");
    }

    @Test
    public void testUpdateScoreById() {
        long id = 1;
        Score score = new Score(1, 150);

        Optional<Score> scoreOptional = Optional.of(score);

        when(scoreRepository.findById(String.valueOf(id))).thenReturn(scoreOptional.get());
        when(scoreRepository.save(any(Score.class))).thenReturn(score);

        scoreService.updateScoreById(id, score);

        verify(scoreRepository, times(1)).findById(String.valueOf(id));
        verify(scoreRepository, times(1)).save(any(Score.class));
    }

    @Test
    public void testAddScore() {
        Score score = new Score(1, 100);

        scoreService.addScore(score);

        verify(scoreRepository).save(score);
    }

    @Test
    public void testDeleteScoreById() {
        long id = 1;

        doNothing().when(scoreRepository).deleteById(id);

        scoreService.deleteScoreById(id);

        verify(scoreRepository, times(1)).deleteById(id);
    }

    @Test
    public void getUserByUserName_shouldReturnNull_whenUserDoesNotExist() {
        String username = "johndoe";
        when(userRepository.findById(username)).thenReturn(Optional.empty());

        User result = userService.getUserByUserName(username);

        assertNull(result);
    }

    @Test
    public void updateExistingUser_shouldReturnNull_whenUserDoesNotExist() {
        String username = "johndoe";
        User user = new User(username, "newpassword", 200, 20, 10);
        when(userRepository.existsById(username)).thenReturn(false);

        User result = userService.updateExistingUser(user);

        assertNull(result);
        verify(userRepository, never()).save(any(UserRecord.class));
    }

    @Test
    public void testAddNewUser() {
        String username = "testUser";
        User user = new User(username, "password", 100, 0, 0);

        User newUser = userService.addNewUser(user);

        Assertions.assertNotNull(newUser);
        assertEquals(newUser.getUserName(), username);
        assertEquals(newUser.getPassword(), "password");
        assertEquals(newUser.getPurse(), 100);
        assertEquals(newUser.getWins(), 0);
        assertEquals(newUser.getLosses(), 0);
    }

    @Test
    public void testAddExistingUser() {
        String username = "testUser";
        UserRecord userRecord = new UserRecord();
        userRecord.setUsername(username);
        userRecord.setPassword("password");
        userRecord.setPurse(100);
        userRecord.setWins(0);
        userRecord.setLosses(0);
        userRepository.save(userRecord);

        User user = new User(username, "password", 100, 0, 0);

        User newUser = userService.addNewUser(user);

        Assertions.assertNull(null);
    }


}