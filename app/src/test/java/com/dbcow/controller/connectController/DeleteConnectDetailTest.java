package com.dbcow.controller.connectController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class DeleteConnectDetailTest {

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
    void deleteUserDetailTest1() throws Exception {
        mockMvc.perform(delete("/api/connect/con5").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void deleteUserDetailTest2() throws Exception {
        mockMvc.perform(delete("/api/connect/con5").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void deleteUserDetailTest3() throws Exception {
        mockMvc.perform(delete("/api/connect/con1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, util.getMessage("M1000011", new String[]{"con1"})))));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void deleteUserDetailTest4() throws Exception {
        mockMvc.perform(delete("/api/connect/xxxx").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, util.getMessage("M1000011", new String[]{"xxxx"})))));
    }
    
    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void deleteUserDetailTest5() throws Exception {
        mockMvc.perform(delete("/api/connect/").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserDetailTest6() throws Exception {
        mockMvc.perform(delete("/api/connect/con5").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}