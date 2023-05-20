package com.dbcow.service.userService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.repository.UserRepository;
import com.dbcow.service.UserService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class LoadUserByUsernameTest {

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
	void loadUserByUsernameTest1() {
		userDetails = userService.loadUserByUsername("user1");
		assertThat(userDetails.getUsername(), is("user1"));
	}

	@Test
	void loadUserByUsernameTest2() {
		UsernameNotFoundException ex;
		ex = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("xxxx"));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));

		ex = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(null));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { null })));
	}
}