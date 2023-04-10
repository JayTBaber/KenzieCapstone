package com.kenzie.appserver.service;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kenzie.appserver.config.DynamoDbConfig;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.PlayerRepository;
import com.kenzie.appserver.repositories.model.PlayerRecord;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.appserver.service.model.Player;
import com.kenzie.appserver.service.model.Score;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kenzie.appserver.dao.GameDAO;
import com.kenzie.appserver.dao.PlayerDAO;
import com.kenzie.appserver.dao.ScoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;



public class ServiceTests {

    @Mock
    private PlayerRepository playerRepository;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private LambdaServiceClient lambdaServiceClient;

    @Value("${dynamodb.Players}")
    private String tableName;

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
    @Test
    public void testStartNewGame() {
        String userId = "1234";
        GameData gameData = new GameData();
        gameData.setGameId("1234");
        gameData.setPlayerId(UUID.randomUUID().toString());

        // mock the lambda service client
        when(lambdaServiceClient.setGameData(userId)).thenReturn(gameData);

        // call the startNewGame method
        GameResponse gameResponse = gameService.startNewGame(userId);

        // verify the response
        Assertions.assertNotNull(gameResponse);
        assertEquals("1234", gameResponse.getGameId());

        // verify that the game is saved in the repository
        verify(gameRepository, times(1)).save(any(Game.class));
    }
}