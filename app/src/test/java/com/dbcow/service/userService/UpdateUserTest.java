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
public class UpdateUserTest {

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
	void updateUserTest1() {
		user.setUsername("user1");
		user.setPassword("updateUser1");
		user.setRoles("ROLE_ADMIN");
		userService.updateUser(user);
		user = userRepository.findByUsernameNoDeleteFlag("user1").get();
		assertThat(user.getPassword(), is("updateUser1"));
		assertThat(user.getRoles(), is("ROLE_ADMIN"));
	}

	@Test
	void updateUserTest2() {
		user.setUsername("xxxx");
		user.setPassword("updateUser2");
		user.setRoles("ROLE_USER");
		ex = assertThrows(CustomErrorException.class, () -> userService.updateUser(user));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));
	}
}