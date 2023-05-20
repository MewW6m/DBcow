package com.dbcow.controller.userController;

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

import com.dbcow.config.ErrorHandler;
import com.dbcow.controller.UserController;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class DetailTest {

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
    void detailTest1() throws Exception {
        mockMvc.perform(get("/user/user2"))
                .andExpect(status().isOk())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("user/detail"));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void detailTest2() throws Exception {
        mockMvc.perform(get("/user/dsagabdsadsa"))
        .andExpect(status().isOk())
        // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(view().name("user/detail"));
    }
}