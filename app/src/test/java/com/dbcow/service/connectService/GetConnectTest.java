package com.dbcow.service.connectService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
public class GetConnectTest {

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
	void getUserTest1() {
		connect = connectService.getConnect("con5", "user2");
		assertThat(connect.getConname(), is("con5"));
	}

	@Test
	void getUserTest2() {
		ex = assertThrows(CustomErrorException.class, () -> connectService.getConnect("xxxx", "user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000011", new String[] { "xxxx" })));

		ex = assertThrows(CustomErrorException.class, () -> connectService.getConnect("con5", "xxxx"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000011", new String[] { "con5" })));
	}

	@Test
	void getUserTest3() {
		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> connectService.getConnect(null, "user2"));
	}
}