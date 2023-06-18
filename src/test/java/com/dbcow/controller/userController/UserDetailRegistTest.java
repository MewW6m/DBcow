package com.dbcow.controller.userController;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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

import com.dbcow.controller.UserController;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class UserDetailRegistTest {

    private MockMvc mockMvc;
    @Autowired
    UserController userController;
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
    void registTest1() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("user/userRegist"));
    }

    @Test
    @WithMockUser(username="user1", roles={"ADMIN"})
    void registTest2() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table"));
    }
    

    @Test
    @WithMockUser(username="user1", roles={"USER"})
    void registTest3() throws Exception {
        mockMvc.perform(get("/user/regist"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table"));
    }
}