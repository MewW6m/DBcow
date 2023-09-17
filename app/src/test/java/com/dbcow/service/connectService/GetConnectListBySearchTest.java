package com.dbcow.service.connectService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Connect;
import com.dbcow.repository.ConnectRepository;
import com.dbcow.service.ConnectService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest

public class GetConnectListBySearchTest {
    
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
        List searchParamList = new ArrayList() {{add(Triple.of("conname","CO","con5"));}};
		List<Connect> result = connectService.getConnectListBySearch(searchParamList, "conname", "asc", 100, 0);
		assertThat(result.get(0).getConname(), is("con5"));
	}

	@Test
	void getUserTest2() {
        List searchParamList = new ArrayList();
		connectService.getConnectListBySearch(searchParamList, null, null, null, null);
	}
}