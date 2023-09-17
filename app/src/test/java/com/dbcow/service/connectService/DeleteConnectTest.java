package com.dbcow.service.connectService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Connect;
import com.dbcow.repository.ConnectRepository;
import com.dbcow.service.ConnectService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class DeleteConnectTest {

	@Autowired
	ConnectService connectService;
	@Autowired
	ConnectRepository connectRepository;
	@Autowired
	Util util;
	private Connect connect;
	private CustomErrorException ex;

	@BeforeEach
	void setup() {
		connect = new Connect();
		ex = new CustomErrorException(0, null);
	}

	@Test
	void deleteUserTest1() {
		connectService.deleteConnect("con5", "user2");
		Optional<Connect> connectOpt = connectRepository.findByConnameAndUsername("con5", "user2");
		assertThat(connectOpt.isPresent(), is(false));
	}

	@Test
	void deleteUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> connectService.deleteConnect("xxxx", "user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000011", new String[] { "xxxx" })));
	}
}