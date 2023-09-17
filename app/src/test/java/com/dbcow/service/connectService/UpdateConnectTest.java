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
public class UpdateConnectTest {

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
	void updateConnectTest1() {
		connect.setConname("con5");
		connect.setUser("user2");
		connectService.updateConnect(connect, "user2");
		connect = connectRepository.findByConnameAndUsername("con5", "user2").get();
		assertThat(connect.getUser(), is("user2"));
	}

	@Test
	void updateConnectTest2() {
		connect.setConname("xxxx");
		connect.setUser("user2");
		ex = assertThrows(CustomErrorException.class, () -> connectService.updateConnect(connect, "user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000011", new String[] { "xxxx" })));
	}

}