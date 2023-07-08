package com.dbcow.controller.connectController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.dbcow.model.Response;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class PostConnectDetailTest {

    private MockMvc mockMvc;
    @Autowired
    Util util;
    @Autowired
    UserRepository userRepository;
    @Autowired private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
            .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void postUserDetailTest1() throws Exception {
        mockMvc.perform(post("/api/connect/contest1")
                .with(csrf()).content("{\"status\":0,\"conname\":\"contest1\",\"dbtype\":\"3\",\"host\":\"localhost\",\"user\":\"user1\",\"password\":\"pass1\",\"dataregistflag\":true,\"dataupdateflag\":true,\"datadeleteflag\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }
    
    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void postUserDetailTest2() throws Exception {
        mockMvc.perform(post("/api/connect/contest2")
                .with(csrf()).content("{\"status\":0,\"conname\":\"contest2\",\"dbtype\":\"3\",\"host\":\"localhost\",\"user\":\"user1\",\"password\":\"pass1\",\"dataregistflag\":true,\"dataupdateflag\":true,\"datadeleteflag\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void postUserDetailTest3() throws Exception {
        mockMvc.perform(post("/api/connect/con5")
                .with(csrf()).content("{\"status\":0,\"conname\":\"con5\",\"dbtype\":\"3\",\"host\":\"localhost\",\"user\":\"user1\",\"password\":\"pass1\",\"dataregistflag\":true,\"dataupdateflag\":true,\"datadeleteflag\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, util.getMessage("M1000012", new String[]{"con5"})))));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void postUserDetailTest4() throws Exception {
        mockMvc.perform(post("/api/connect/")
                .with(csrf()).content("{\"status\":0,\"conname\":\"\",\"dbtype\":\"3\",\"host\":\"localhost\",\"user\":\"user1\",\"password\":\"pass1\",\"dataregistflag\":true,\"dataupdateflag\":true,\"datadeleteflag\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void postUserDetailTest5() throws Exception {
        mockMvc.perform(post("/api/connect/contest1")
                .with(csrf()).content("{\"status\":0,\"conname\":\"contest1\",\"dbtype\":\"3\",\"host\":\"localhost\",\"user\":\"user1\",\"password\":\"pass1\",\"dataregistflag\":true,\"dataupdateflag\":true,\"datadeleteflag\":true}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

}