package com.dbcow.repository.connectRepositoryImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.repository.ConnectRepositoryImpl;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest

public class SelectConnectListTest {
	@Autowired
	ConnectRepositoryImpl connectRepositoryImpl;
	@Autowired
	Util util;

	@BeforeEach
	void setup() {
	}

	@Test
	void selectConnectListTest1() {
        List result;
        result = connectRepositoryImpl.selectConnectList(new ArrayList(), null, null, null, null);
        assertThat(result.size() > 0, is(true));
        result = connectRepositoryImpl.selectConnectList(new ArrayList(), null, null, 1, 0);
        assertThat(result.size(), is(1));
        result = connectRepositoryImpl.selectConnectList(new ArrayList(), "conname", "asc", 1, 0);
        assertThat(result.size(), is(1));
        result = connectRepositoryImpl.selectConnectList(new ArrayList(){{add(Triple.of("conname", "CO", "con"));}}, "conname", "asc", 10, 0);
        assertThat(result.size(), is(10));
	}
    
}
