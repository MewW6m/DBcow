package com.dbcow.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class UserRepositoryTest {


	@Autowired UserRepository userRepository;
	
	@Test
	void xxxTest() {}
	
}