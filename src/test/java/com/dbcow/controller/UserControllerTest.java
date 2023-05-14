package com.dbcow.controller;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.dbcow.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    @Autowired
    UserController userController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    void getRegistTest1() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("user/regist"));
    }

    @Test
    @WithMockUser("user1")
    void getRegistTest2() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table/list"));
    }

    @Test
    void postUserDetailTest1() throws Exception {
        mockMvc.perform(post("/api/user/detail")
                .with(csrf()).content("{\"username\":\"posUDetTest1\", \"password\":\"posUDetTest1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser("user1")
    void postUserDetailTest2() throws Exception {
        mockMvc.perform(post("/api/user/detail")
                .with(csrf()).content("{\"username\":\"posUDetTest2\", \"password\":\"posUDetTest2\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        "{}",
        "{\"username\":\"test\"}",
        "{\"password\":\"test\"}",
        "{\"username\":\"\", \"password\":\"\"}",
        "{\"username\":\"test\", \"password\":\"\"}",
        "{\"username\":\"\", \"password\":\"test\"}",
        "{\"username\":\"test\", \"password\":\"test\", \"id\":9999999}",
        "{\"username\":\"test\", \"password\":\"test\", \"roles\":\"01\"}",
        // "{\"username\":\"test\", \"password\":\"test\", \"createUserDate\":\"2023-01-01\"}",
        // "{\"username\":\"test\", \"password\":\"test\", \"updateUserDate\":\"2023-01-01\"}",
        // "{\"username\":\"test\", \"password\":\"test\", \"deleteFlag\":\"false\"}"
    })
    void postUserDetailTest3(String param) throws Exception {
        mockMvc.perform(post("/api/user/detail")
                .with(csrf()).content(param)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
            }
}