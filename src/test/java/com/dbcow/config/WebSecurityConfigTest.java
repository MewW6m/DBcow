package com.dbcow.config;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@SpringBootTest
public class WebSecurityConfigTest {

    private MockMvc mockMvc;

    @Autowired private WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void securityFilterChainTest_formlogin1() throws Exception {
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", "user")
                .param("password", "password"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table/list"));
    }

    @Test
    @WithMockUser("user")
    void securityFilterChainTest_formlogin2() throws Exception {
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", "user")
                .param("password", "password"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/table/list"));
    }

    @Test
    void securityFilterChainTest_formlogin3() throws Exception {
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", "")
                .param("password", ""))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void securityFilterChainTest_formlogin4() throws Exception {
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", "user")
                .param("password", "test"))
                .andExpect(status().isFound())
                // .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void securityFilterChainTest_authorizeHttpRequests1() throws Exception {
        List<String> resUrlList = new ArrayList();
        resUrlList.add("/css/common/body.css");
        resUrlList.add("/js/body.js");
        resUrlList.add("/img/favicon.ico");
        resUrlList.add("/user/regist");

        for (String resUrl : resUrlList) {
            mockMvc.perform(get(resUrl))
                    .andExpect(status().is(oneOf(200, 302)));
        }

        mockMvc.perform(post("/api/user/detail").with(csrf()))
                .andExpect(status().is(oneOf(200, 302)));
    }

    @Test
    @WithMockUser("user")
    void securityFilterChainTest_authorizeHttpRequests2() throws Exception {
        List<String> resUrlList = new ArrayList();
        resUrlList.add("/css/common/body.css");
        resUrlList.add("/js/body.js");
        resUrlList.add("/img/favicon.ico");
        resUrlList.add("/user/regist");

        for (String resUrl : resUrlList) {
            mockMvc.perform(get(resUrl))
                    .andExpect(status().is(oneOf(200, 302)));
        }
    }

    @Test
    void securityFilterChainTest_authorizeHttpRequests3() throws Exception {
        mockMvc.perform(get("/table/list"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("**/login"));
        mockMvc.perform(get("/api/user/detail")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("**/login"));
        mockMvc.perform(post("/api/user/detail")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden());
        mockMvc.perform(put("/api/user/detail")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/user/detail")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden());
    }

}