package com.dbcow.controller.userController;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.ErrorHandler;
import com.dbcow.controller.UserController;
import com.dbcow.model.Response;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class GetUserDetailTest {

    private MockMvc mockMvc;
    @Autowired
    UserController userController;
    @Autowired
    Util util;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
            .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void getUserDetailTest1() throws Exception {
        mockMvc.perform(get("/api/user/detail?username=user1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                        new Response(200, userRepository.findByUsernameNoDeleteFlag("user1").get()))));
    }

    @Test
    @WithMockUser(username="user1")
    void getUserDetailTest2() throws Exception {
        mockMvc.perform(get("/api/user/detail?username=user1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, "Access Denied"))));
    }
    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void getUserDetailTest3() throws Exception {
        mockMvc.perform(get("/api/user/detail?username=xxxx").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, util.getMessage("M1000004", new String[]{"xxxx"})))));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "?test=xxxx",
            "?username=",
            "?username=xxxx",
    })
    @WithMockUser(username="user2", roles={"ADMIN"})
    void getUserDetailTest4(String param) throws Exception {
        mockMvc.perform(delete("/api/user/detail" + param)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
    }

}