//package com.kenzie.appserver.controller;
//
//import com.kenzie.appserver.service.GameService;
//import com.kenzie.appserver.service.PlayerService;
//import com.kenzie.appserver.service.ScoreService;
//import com.kenzie.appserver.service.model.Game;
//import com.kenzie.appserver.service.model.Player;
//import com.kenzie.appserver.service.model.Score;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.Assert.assertNotNull;
//
//public class IntegrationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private GameService gameService;
//
//    @Autowired
//    private PlayerService playerService;
//
//    @Autowired
//    private ScoreService scoreService;
//
//    @BeforeEach
//    public void setUp() {
//        // Initialize the database with some test data
//        Game game1 = new Game("Chess");
//        Game game2 = new Game("Checkers");
//        gameService.addGame(game1);
//        gameService.addGame(game2);
//
//        Player player1 = new Player("John");
//        Player player2 = new Player("Jane");
//        playerService.addPlayer(player1);
//        playerService.addPlayer(player2);
//
//        Score score1 = new Score(1, 1, 10);
//        Score score2 = new Score(2, 1, 20);
//        Score score3 = new Score(2, 2, 30);
//        scoreService.addScore(score1);
//        scoreService.addScore(score2);
//        scoreService.addScore(score3);
//    }
//
//    @Test
//    public void testGetGameById() throws Exception {
//        mockMvc.perform(get("/games/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Chess"));
//    }
//
//    @Test
//    public void testGetAllGames() throws Exception {
//        mockMvc.perform(get("/games"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Chess"))
//                .andExpect(jsonPath("$[1].name").value("Checkers"));
//    }
//
//    @Test
//    public void testAddGame() throws Exception {
//        Game gameToAdd = new Game("Monopoly");
//        mockMvc.perform(post("/games")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(gameToAdd)))
//                .andExpect(status().isOk());
//
//        Game addedGame = gameService.getGameByName("Monopoly");
//        assertNotNull(addedGame);
//    }
//
//    @Test
//    public void testGetPlayerById() throws Exception {
//        mockMvc.perform(get("/players/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John"));
//    }
//
//    @Test
//    public void testGetAllPlayers() throws Exception {
//        mockMvc.perform(get("/players"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("John"))
//                .andExpect(jsonPath("$[1].name").value("Jane"));
//    }
//
//    @Test
//    public void testAddPlayer() throws Exception {
//        Player playerToAdd = new Player("Bob");
//        mockMvc.perform(post("/players")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(playerToAdd)))
//                .andExpect(status().isOk());
//
//        Player addedPlayer = playerService.getPlayerByName("Bob");
//        assertNotNull(addedPlayer);
//    }
//
//    @Test
//    public void testGetScoreById() throws Exception {
//        mockMvc.perform(get("/scores/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.value").value(10));
//    }
//
//    @Test
//    public void testGetScoresForPlayer() throws Exception {
//        mockMvc.perform(get("/scores?playerId=2"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].value").value(20))
//                .andExpect(jsonPath("$[1].value").value(30));
//    }
//
//    @Test
//    public void testAddScore() throws Exception {
//        Score scoreToAdd = new Score(1, 2, 40);
//        mockMvc.perform(post("/scores")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(scoreToAdd)))
//                .andExpect(status().isOk());
//        Score addedScore = scoreService.getScoreByPlayerAndGame(2, 1);
//        assertNotNull(addedScore);
//    }
//}
