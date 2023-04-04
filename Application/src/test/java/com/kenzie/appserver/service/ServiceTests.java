package com.kenzie.appserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.appserver.service.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kenzie.appserver.dao.GameDAO;
import com.kenzie.appserver.dao.PlayerDAO;
import com.kenzie.appserver.dao.ScoreDAO;


public class ServiceTests {

    @Mock
    private GameDAO gameDao;

    @Mock
    private PlayerDAO playerDao;

    @Mock
    private ScoreDAO scoreDao;

    @InjectMocks
    private GameService gameService;

    @InjectMocks
    private PlayerService playerService;

    @InjectMocks
    private ScoreService scoreService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    // Tests for GameService

    @Test
    public void testGetGameById() {
        Game expectedGame = new Game(1, "Chess");
        when(gameDao.getGameById(1)).thenReturn(expectedGame);
        Game actualGame = gameService.getGameById(1);
        assertEquals(expectedGame, actualGame);
    }

    @Test
    public void testGetAllGames() {
        List<Game> expectedGames = new ArrayList<>();
        expectedGames.add(new Game(1, "Chess"));
        expectedGames.add(new Game(2, "Checkers"));
        when(gameDao.getAllGames()).thenReturn(expectedGames);
        List<Game> actualGames = gameService.getAllGames();
        assertEquals(expectedGames, actualGames);
    }

    @Test
    public void testAddGame() {
        Game gameToAdd = new Game(1, "Chess");
        gameService.addGame(gameToAdd);
        verify(gameDao, times(1)).addGame(gameToAdd);
    }

    // Tests for PlayerService

    @Test
    public void testGetPlayerById() {
        Player expectedPlayer = new Player(1, "John");
        when(playerDao.getPlayerById(1)).thenReturn(expectedPlayer);
        Player actualPlayer = playerService.getPlayerById(1);
        assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player(1, "John"));
        expectedPlayers.add(new Player(2, "Jane"));
        when(playerDao.getAllPlayers()).thenReturn(expectedPlayers);
        List<Player> actualPlayers = playerService.getAllPlayers();
        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void testAddPlayer() {
        Player playerToAdd = new Player(1, "John");
        playerService.addPlayer(playerToAdd);
        verify(playerDao, times(1)).addPlayer(playerToAdd);
    }

    // Tests for ScoreService

    @Test
    public void testGetScoreById() {
        Score expectedScore = new Score(1, 1, 1, 10);
        when(scoreDao.getScoreById(1)).thenReturn(expectedScore);
        Score actualScore = scoreService.getScoreById(1);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testGetScoresForPlayer() {
        List<Score> expectedScores = new ArrayList<>();
        expectedScores.add(new Score(1, 1, 1, 10));
        expectedScores.add(new Score(2, 1, 2, 20));
        when(scoreDao.getScoresForPlayer(1)).thenReturn(expectedScores);
        List<Score> actualScores = scoreService.getScoresForPlayer(1);
        assertEquals(expectedScores, actualScores);
    }

    @Test
    public void testGetScoresForGame() {
        List<Score> expectedScores = new ArrayList<>();
        expectedScores.add(new Score(1, 1, 1, 10));
        expectedScores.add(new Score(2, 2, 1, 15));
        when(scoreDao.getScoresForGame(1)).thenReturn(expectedScores);
        List<Score> actualScores = scoreService.getScoresForGame(1);
        assertEquals(expectedScores, actualScores);
    }

    @Test
    public void testAddScore() {
        Score scoreToAdd = new Score(1, 1, 1, 10);
        scoreService.addScore(scoreToAdd);
        verify(scoreDao, times(1)).addScore(scoreToAdd);
    }

    @Test
    public void testGetTopScoresForGame() {
        List<Score> expectedScores = new ArrayList<>();
        expectedScores.add(new Score(1, 1, 1, 10));
        expectedScores.add(new Score(2, 2, 1, 15));
        when(scoreDao.getTopScoresForGame(1)).thenReturn(expectedScores);
        List<Score> actualScores = scoreService.getTopScoresForGame(1);
        assertEquals(expectedScores, actualScores);
    }
}
