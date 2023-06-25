package com.dbcow.controller.settingController;

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

public class SettingDetailTest {
    private MockMvc mockMvc;
    @Autowired private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
            .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void userListTest1() throws Exception {
        mockMvc.perform(get("/setting"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("setting/settingDetail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"USER"})
    void userListTest2() throws Exception {
        mockMvc.perform(get("/setting"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("setting/settingDetail"));
    }
    
}
