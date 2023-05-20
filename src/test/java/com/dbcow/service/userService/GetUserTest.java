package com.dbcow.service.userService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
public class GetUserTest {

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
		userDetails = userService.getUser("user1", true);
		assertThat(userDetails.getUsername(), is("user1"));
	}

	@Test
	void getUserTest2() {
		userDetails = userService.getUser("user2", false);
		assertThat(userDetails.getUsername(), is("user2"));
	}

	@Test
	void getUserTest3() {
		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("xxxx", true));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));

		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("user3", true));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "user3" })));

		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> userService.getUser(null, false));

	}

	@Test
	void getUserTest4() {
		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("xxxx", true));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));

		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> userService.getUser(null, true));
	}
}