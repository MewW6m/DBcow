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
public class RegistConnectTest {

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
	void registConnectTest1() {
        connect.setConname("registTest1");
        connect.setDbtype(1);
        connect.setHost("localhost");
		connect.setUser("user");
		connect.setPassword("pass");
		connect.setStatus(1);
		connect.setUsername("user2");
		connect.setDataregistflag(true);
		connect.setDataupdateflag(true);
		connect.setDatadeleteflag(true);
		connect.setConnectstring("aaa");
        
		connectService.registConnect(connect, "user2");

		Connect result = connectRepository.findByConnameAndUsername("registTest1", "user2").get();
		assertThat(result.getConname(), is("registTest1"));
		assertThat(result.getDbtype(), is(1));
		assertThat(result.getHost(), is("localhost"));
		assertThat(result.getUser(), is("user"));
		assertThat(result.getPassword(), is("pass"));
		assertThat(result.getStatus(), is(1));
		assertThat(result.getUsername(), is("user2"));
		assertThat(result.getDataregistflag(), is(true));
		assertThat(result.getDatadeleteflag(), is(true));
		assertThat(result.getDatadeleteflag(), is(true));
		assertThat(result.getConnectstring(), is("aaa"));
	}

	@Test
	void registConnectTest2() {
        connect.setConname("con5");
		ex = assertThrows(CustomErrorException.class, () -> connectService.registConnect(connect, "user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000012", new String[]{"con5"})));
	}

	@Test
	void registConnectTest3() {
        connect.setConname("registTest2");
		ex = assertThrows(CustomErrorException.class, () -> connectService.registConnect(connect, "user2"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000013")));
	}

	@Test
	void registConnectTest4() {
		assertThrows(NullPointerException.class, () -> connectService.registConnect(null, "user2"));
	}
}