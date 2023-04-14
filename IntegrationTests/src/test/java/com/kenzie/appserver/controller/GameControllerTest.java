package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.GameRequest;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.service.GameService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class GameControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    GameService gameService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {

        String name = mockNeat.strings().valStr();

        GameResponse persistedGame = gameService.startNewGame(name);
        mvc.perform(get("/game/{id}", persistedGame.getGameId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("gameId")
                        .isString())
                .andExpect(jsonPath("playerId")
                        .value(is(name)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createExample_CreateSuccessful() throws Exception {
        String name = mockNeat.strings().valStr();

        GameRequest gameRequest = new GameRequest(name, 100);
        gameRequest.setPlayerId(name);

        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/game")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(gameRequest)))
                .andExpect(jsonPath("gameId")
                        .exists())
                .andExpect(jsonPath("playerId")
                        .value(is(name)))
                .andExpect(status().is2xxSuccessful());
    }
}