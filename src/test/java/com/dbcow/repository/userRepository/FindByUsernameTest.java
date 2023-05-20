package com.dbcow.repository.userRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.repository.UserRepository;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class FindByUsernameTest {
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
}