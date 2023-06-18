package com.dbcow.repository.userRepositoryImpl;

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

import com.dbcow.repository.UserRepositoryImpl;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest

public class SelectUserListTest {
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	@Autowired
	Util util;

	@BeforeEach
	void setup() {
	}

	@Test
	void findByUsernameTest1() {
        List result;
        result = userRepositoryImpl.selectUserList(new ArrayList(), null, null, null, null);
        assertThat(result.size() > 0, is(true));
        result = userRepositoryImpl.selectUserList(new ArrayList(), null, null, 1, 0);
        assertThat(result.size(), is(1));
        result = userRepositoryImpl.selectUserList(new ArrayList(), "username", "asc", 1, 0);
        assertThat(result.size(), is(1));
        result = userRepositoryImpl.selectUserList(new ArrayList(){{add(Triple.of("username", "CO", "user"));}}, "username", "asc", 10, 0);
        assertThat(result.size(), is(4));
	}
    
}
