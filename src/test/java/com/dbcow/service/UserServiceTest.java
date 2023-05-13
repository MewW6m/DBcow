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
	private CustomUserDetails customUserDetails;
	private UserDetails userDetails;
	private CustomErrorException ex;

	@BeforeEach
	void setup() {
		customUserDetails = new CustomUserDetails();
		ex = new CustomErrorException(0, null);
	}

	@Test
	void loadUserByUsernameTest1() {
		userDetails = userService.loadUserByUsername("user");
		assertThat(userDetails.getUsername(), is("user"));
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
		userDetails = userService.getUser("user");
		assertThat(userDetails.getUsername(), is("user"));
	}

	@Test
	void getUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> userService.getUser("xxxx")); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{"xxxx"})));
		
		ex = assertThrows(CustomErrorException.class, () -> userService.getUser(null)); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[]{null})));
	}

	@Test
	void registUserTest1() {
		customUserDetails.setUsername("registUserTest1User");
		customUserDetails.setPassword("registUserTest1Pass");
		userService.registUser(customUserDetails);

		CustomUserDetails result = userRepository.findByUsername("registUserTest1User").get();
		assertThat(result.getUsername(), is("registUserTest1User"));
		assertThat(result.getPassword(), is("registUserTest1Pass"));
		assertThat(result.getRoles(), is("01"));
		assertThat(result.getEnableFlag(), is(true));
	}

	@Test
	void registUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> userService.registUser(customUserDetails)); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000003")));
	}

	@Test
	void registUserTest3() {
		customUserDetails.setUsername("user");
		customUserDetails.setPassword("test");
		ex = assertThrows(CustomErrorException.class, () -> userService.registUser(customUserDetails)); 
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000005", new String[]{customUserDetails.getUsername()})));
	}

	@Test
	void registUserTest4() {
		assertThrows(NullPointerException.class, () -> userService.registUser(null)); 
	}
}