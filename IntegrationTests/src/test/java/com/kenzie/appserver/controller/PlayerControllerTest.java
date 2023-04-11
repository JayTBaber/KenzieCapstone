package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.PlayerService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@IntegrationTest
class PlayerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    PlayerService playerService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getById_Exists() throws Exception {

        String name = mockNeat.strings().valStr();

        PlayerResponse persistedPlayer = playerService.createNewPlayer(name);
        mvc.perform(get("/player/{id}", persistedPlayer.getPlayerId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("playerId")
                        .isString())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createExample_CreateSuccessful() throws Exception {
        String name = mockNeat.strings().valStr();

        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setPlayerId(name);

        mapper.registerModule(new JavaTimeModule());

        mvc.perform(post("/player")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(playerRequest)))
                .andExpect(jsonPath("playerId")
                        .exists())
                .andExpect(status().is2xxSuccessful());
    }
}