package com.dbcow.controller.settingController;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
import org.springframework.web.context.WebApplicationContext;

import com.dbcow.model.Response;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class PatchSettingDetailTest {

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
    @WithMockUser(username="user2", roles={"ADMIN"})
    void patchSettingDetailTest1() throws Exception {
        mockMvc.perform(patch("/api/setting")
                .with(csrf()).content("{\"2\":\"う\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="user1", roles={"USER"})
    void patchSettingDetailTest2() throws Exception {
        mockMvc.perform(patch("/api/setting")
                .with(csrf()).content("{\"2\":\"う\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new Response(200, ""))));
    }

    @Test
    @WithMockUser(username="xxxx", roles={"ADMIN"})
    void patchSettingDetailTest3() throws Exception {
        mockMvc.perform(patch("/api/setting")
        .with(csrf()).content("{\"2\":\"う\"}")
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
            "{\"xxxx\":\"う\"}",
    })
    @WithMockUser(username="user2", roles={"ADMIN"})
    void patchSettingDetailTest3(String param) throws Exception {
        mockMvc.perform(patch("/api/user/detail")
                .with(csrf()).content(param)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(oneOf(400, 500)));
    }

    @Test
    void patchSettingDetailTest4() throws Exception {
        mockMvc.perform(patch("/api/setting")
                .with(csrf()).content("{\"2\":\"う\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}