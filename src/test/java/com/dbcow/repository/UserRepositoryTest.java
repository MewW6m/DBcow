package com.dbcow.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	@Autowired
	Util util;

	@BeforeEach
	void setup() {
	}

	@Test
	void findByUsernameTest1() {
		assertThat(userRepository.findByUsername("user1").isPresent(), is(true));
		assertThat(userRepository.findByUsername("user2").isPresent(), is(true));
	}

	@Test
	void findByUsernameTest2() {
		assertThat(userRepository.findByUsername("xxxx").isPresent(), is(false));
		assertThat(userRepository.findByUsername("").isPresent(), is(false));
		assertThat(userRepository.findByUsername("user3").isPresent(), is(false));
		assertThat(userRepository.findByUsername("user4").isPresent(), is(false));
		assertThat(userRepository.findByUsername(null).isPresent(), is(false));
	}

	@Test
	void findByUsernameNoDeleteFlagTest1() {
		assertThat(userRepository.findByUsernameNoDeleteFlag("user1").isPresent(), is(true));
		assertThat(userRepository.findByUsernameNoDeleteFlag("user2").isPresent(), is(true));
		assertThat(userRepository.findByUsernameNoDeleteFlag("user3").isPresent(), is(true));
		assertThat(userRepository.findByUsernameNoDeleteFlag("user4").isPresent(), is(true));
	}

	@Test
	void findByUsernameNoDeleteFlagTest2() {
		assertThat(userRepository.findByUsernameNoDeleteFlag("xxxx").isPresent(), is(false));
		assertThat(userRepository.findByUsernameNoDeleteFlag("").isPresent(), is(false));
		assertThat(userRepository.findByUsernameNoDeleteFlag(null).isPresent(), is(false));
	}
}