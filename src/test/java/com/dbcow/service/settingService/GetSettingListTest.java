package com.dbcow.service.settingService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Setting;
import com.dbcow.repository.SettingRepository;
import com.dbcow.service.SettingService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class GetSettingListTest {

	@Autowired
	SettingService settingService;
	@Autowired
	SettingRepository settingRepository;
	@Autowired
	Util util;
	private Setting setting;
    private List<Setting> settingList;
	private CustomErrorException ex;

	@BeforeEach
	void setup() {
		setting = new Setting();
		ex = new CustomErrorException(0, null);
	}

	@Test
	void getSettingListTest1() {
		settingList = settingService.getSettingList("user1");
		assertThat(settingList.size() > 0, is(true));
	}

	@Test
	void getSettingListTest2() {
		settingList = settingService.getSettingList("user2");
		assertThat(settingList.size() > 0, is(true));
	}

	@Test
	void getSettingListTest3() {
		ex = assertThrows(CustomErrorException.class, () -> settingService.getSettingList("xxxx"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));

		ex = assertThrows(CustomErrorException.class, () -> settingService.getSettingList("user3"));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "user3" })));

	}

	@Test
	void getSettingListTest4() {
		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> settingService.getSettingList(null));
	}
}