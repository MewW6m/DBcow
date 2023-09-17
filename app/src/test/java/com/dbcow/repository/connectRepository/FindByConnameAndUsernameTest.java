package com.dbcow.repository.connectRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.repository.ConnectRepository;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class FindByConnameAndUsernameTest {
	@Autowired
	ConnectRepository connectRepository;
	@Autowired
	Util util;

	@BeforeEach
	void setup() {
	}

	@Test
	void findByUsernameNoDeleteFlagTest1() {
		assertThat(connectRepository.findByConnameAndUsername("con1", "user1").isPresent(), is(true));
		assertThat(connectRepository.findByConnameAndUsername("con5", "user2").isPresent(), is(true));
	}

	@Test
	void findByUsernameNoDeleteFlagTest2() {
		assertThat(connectRepository.findByConnameAndUsername("con5", "xxxx").isPresent(), is(false));
		assertThat(connectRepository.findByConnameAndUsername("xxxx", "user2").isPresent(), is(false));
		assertThat(connectRepository.findByConnameAndUsername("con1", "user2").isPresent(), is(false));
		assertThat(connectRepository.findByConnameAndUsername("", "").isPresent(), is(false));
		assertThat(connectRepository.findByConnameAndUsername("con8", "user2").isPresent(), is(false));
	}
}