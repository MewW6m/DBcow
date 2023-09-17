package com.dbcow.service.userService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.repository.UserRepository;
import com.dbcow.service.UserService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest

public class GetUserListBySearchTest {
    
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Util util;
	private CustomUserDetails user;
	private UserDetails userDetails;
	private CustomErrorException ex;

	@BeforeEach
	void setup() {
		user = new CustomUserDetails();
		ex = new CustomErrorException(0, null);
	}

	@Test
	void getUserTest1() {
        List searchParamList = new ArrayList() {{add(Triple.of("username","CO","user1"));}};
		List<CustomUserDetails> result = userService.getUserListBySearch(searchParamList, "username", "asc", 100, 0);
		assertThat(result.get(0).getUsername(), is("user1"));
	}

	@Test
	void getUserTest2() {
        List searchParamList = new ArrayList();
		userService.getUserListBySearch(searchParamList, null, null, null, null);
	}
}