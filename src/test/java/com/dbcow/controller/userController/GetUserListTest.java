package com.dbcow.controller.userController;


import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
import org.springframework.web.context.WebApplicationContext;

import com.dbcow.controller.UserController;
import com.dbcow.model.Response;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@SpringBootTest
public class GetUserListTest {

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
    @WithMockUser(username="user2", roles={"ADMIN"})
    void getUserDetailTest1() throws Exception {
        mockMvc.perform(get("/api/user").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
                //.andExpect(content().string(new ObjectMapper().writeValueAsString(
                  //      new Response(200, userRepository.findByUsernameNoDeleteFlag("user1").get()))));
    }

    @Test
    @WithMockUser(username="user1", roles={"USER"})
    void getUserDetailTest2() throws Exception {
        mockMvc.perform(get("/api/user").with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new ObjectMapper().writeValueAsString(
                    new Response(500, "Access Denied"))));
    }

    @Test
    @WithMockUser(username="user2", roles={"ADMIN"})
    void getUserDetailTest3() throws Exception {
        mockMvc.perform(get(
            "/api/user?searchItem1=username&searchType1=CO&searchValue1=user&searchItem2=roles&searchType2=CO&searchValue2=USER&searchItem3=deleteFlag&searchType3=EQ&searchValue3=false&searchItem4=createDate&searchType4=MT&searchValue4=2023-01-01&searchItem5=updateDate&searchType5=MT&searchValue5=2023-01-01&sortItem=username&sortDirc=asc&pageLimit=100&pageOffset=0"
        ).with(csrf())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}