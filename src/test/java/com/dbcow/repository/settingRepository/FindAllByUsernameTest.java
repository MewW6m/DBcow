package com.dbcow.repository.settingRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.repository.SettingRepository;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class FindAllByUsernameTest {
	@Autowired
	SettingRepository settingRepository;
	@Autowired
	Util util;

	@BeforeEach
	void setup() {
	}

	@Test
	void findAllByUsernameTest1() {
		assertThat(settingRepository.findAllByUsername("user1").size() > 1, is(true));
		assertThat(settingRepository.findAllByUsername("user2").size() > 1, is(true));
	}

	@Test
	void findAllByUsernameTest2() {
		assertThat(settingRepository.findAllByUsername("xxxx").size() > 1, is(false));
		assertThat(settingRepository.findAllByUsername("").size() > 1, is(false));
		assertThat(settingRepository.findAllByUsername("user3").size() > 1, is(false));
		assertThat(settingRepository.findAllByUsername("user4").size() > 1, is(false));
		assertThat(settingRepository.findAllByUsername(null).size() > 1, is(false));
	}
}