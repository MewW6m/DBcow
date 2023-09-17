package com.dbcow.controller.connectController;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@SpringBootTest
public class ConnectDetailTest {

    private MockMvc mockMvc;
    @Autowired private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
            .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void detailTest1() throws Exception {
        mockMvc.perform(get("/connect/con5"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("connect/connectDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void detailTest2() throws Exception {
        mockMvc.perform(get("/connect/regist"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("connect/connectDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void detailTest3() throws Exception {
        mockMvc.perform(get("/connect/con5"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("connect/connectDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void detailTest4() throws Exception {
        mockMvc.perform(get("/connect/regist"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("connect/connectDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void detailTest5() throws Exception {
        mockMvc.perform(get("/connect/dsagabdsadsa"))
        .andExpect(status().isOk())
        // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("connect/connectDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void detailTest6() throws Exception {
        mockMvc.perform(get("/connect/con1"))
        .andExpect(status().isOk())
        // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("connect/connectDetail"));
    }


    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void detailTest7() throws Exception {
        mockMvc.perform(get("/connect/"))
        .andExpect(status().isNotFound());
    }
}