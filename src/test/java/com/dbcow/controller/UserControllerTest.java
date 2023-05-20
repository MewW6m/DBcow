package com.dbcow.controller;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.dbcow.model.Response;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class UserControllerTest {

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
    void getRegistTest1() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("user/regist"));
    }

    @Test
    @WithMockUser(username="user1")
    void getRegistTest2() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table"));
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
    @WithMockUser(username="user1")
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
            "{\"username\":\"test\", \"password\":\"test\", \"roles\":\"01\"}"
    })
    void postUserDetailTest3(String param) throws Exception {
        mockMvc.perform(post("/api/user/detail")
                .with(csrf()).content(param)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void patchUserDetailTest1() throws Exception {
        mockMvc.perform(patch("/api/user/detail")
                .with(csrf()).content("{\"username\":\"user1\", \"password\":\"pass\", \"roles\":\"ROLE_USER\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user1", roles={"USER"})
    void patchUserDetailTest2() throws Exception {
        mockMvc.perform(patch("/api/user/detail")
                .with(csrf()).content("{\"username\":\"user1\", \"password\":\"pass\", \"roles\":\"ROLE_USER\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, "Access Denied"))));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void patchUserDetailTest3() throws Exception {
        mockMvc.perform(patch("/api/user/detail")
        .with(csrf()).content("{\"username\":\"xxxx\", \"password\":\"pass\", \"roles\":\"ROLE_USER\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, util.getMessage("M1000004", new String[]{"xxxx"})))));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "{}",
            "{\"username\":\"user1\"}",
            "{\"password\":\"pass\"}",
            "{\"roles\":\"ROLE_ADMIN\"}",
            "{\"username\":\"user1\", \"password\":\"pass\"}",
            "{\"username\":\"user1\", \"roles\":\"ROLE_ADMIN\"}",
            "{\"password\":\"pass\", \"roles\":\"ROLE_ADMIN\"}",
    })
    @WithMockUser(username="user2", roles={"ADMIN"})
    void patchUserDetailTest4(String param) throws Exception {
        mockMvc.perform(patch("/api/user/detail")
                .with(csrf()).content(param)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void deleteUserDetailTest1() throws Exception {
        mockMvc.perform(delete("/api/user/detail?username=user1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user1")
    void deleteUserDetailTest2() throws Exception {
        mockMvc.perform(delete("/api/user/detail?username=user1").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, "Access Denied"))));
    }
    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void deleteUserDetailTest3() throws Exception {
        mockMvc.perform(delete("/api/user/detail?username=xxxx").with(csrf())
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
    void deleteUserDetailTest4(String param) throws Exception {
        mockMvc.perform(delete("/api/user/detail" + param)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
    }
}