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
public class RegistUserTest {

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
	void registUserTest1() {
		user.setUsername("registUserTest1User");
		user.setPassword("registUserTest1Pass");
		userService.registUser(user);

		CustomUserDetails result = userRepository.findByUsername("registUserTest1User").get();
		assertThat(result.getUsername(), is("registUserTest1User"));
		assertThat(result.getPassword(), is("registUserTest1Pass"));
		assertThat(result.getRoles(), is("ROLE_USER"));
	}

	@Test
	void registUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> userService.registUser(user));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000003")));
	}

	@Test
	void registUserTest3() {
		user.setUsername("user1");
		user.setPassword("test");
		ex = assertThrows(CustomErrorException.class, () -> userService.registUser(user));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000005", new String[] { user.getUsername() })));
	}

	@Test
	void registUserTest4() {
		assertThrows(NullPointerException.class, () -> userService.registUser(null));
	}
}