package com.dbcow.service.userService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

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
public class DeleteUserTest {

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
	void deleteUserTest1() {
		userService.deleteUser("user1");
		Optional<CustomUserDetails> userOpt = userRepository.findByUsernameNoDeleteFlag("user1");
		assertThat(userOpt.isPresent(), is(true));
		assertThat(userOpt.get().getDeleteFlag(), is(true));
		userService.deleteUser("user3");
		assertThat(userOpt.isPresent(), is(true));
		assertThat(userOpt.get().getDeleteFlag(), is(true));
	}

	@Test
	void deleteUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> userService.deleteUser(""));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000003")));
	}

	@Test
	void deleteUserTest3() {
		ex = assertThrows(CustomErrorException.class, () -> userService.deleteUser("xxxx"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));
	}

	@Test
	void deleteUserTest4() {
		ex = assertThrows(CustomErrorException.class, () -> userService.deleteUser("user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000008")));
	}
}