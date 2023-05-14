package com.dbcow.service;

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
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class UserServiceTest {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;
	@Autowired Util util;
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
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{"xxxx"})));
		
		ex = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(null)); 
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{null})));
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
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{"xxxx"})));

		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("user3", true)); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{"user3"})));
		
		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> userService.getUser(null, false)); 

	}

	@Test
	void getUserTest4() {
		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("xxxx", true)); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{"xxxx"})));
		
		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> userService.getUser(null, true)); 
	}

	@Test
	void registUserTest1() {
		user.setUsername("registUserTest1User");
		user.setPassword("registUserTest1Pass");
		userService.registUser(user);

		CustomUserDetails result = userRepository.findByUsername("registUserTest1User").get();
		assertThat(result.getUsername(), is("registUserTest1User"));
		assertThat(result.getPassword(), is("registUserTest1Pass"));
		assertThat(result.getRoles(), is("01"));
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
		assertThat(ex.getMessage(), is(util.getMessage("M1000005", new String[]{user.getUsername()})));
	}

	@Test
	void registUserTest4() {
		assertThrows(NullPointerException.class, () -> userService.registUser(null)); 
	}
}