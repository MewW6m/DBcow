package com.dbcow.service.settingService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Setting;
import com.dbcow.repository.SettingRepository;
import com.dbcow.service.SettingService;
import com.dbcow.util.Util;

@Transactional
@SpringBootTest
public class UpdateSettingTest {

	@Autowired
	SettingService settingService;
	@Autowired
	SettingRepository settingRepository;
	@Autowired
	Util util;
	private Setting setting;
    private List<Setting> settingList;
	private UserDetails userDetails;
	private Map<Integer, String> settingParam;
	private CustomErrorException ex;

	@BeforeEach
	void setup() {
		setting = new Setting();
		settingParam = new HashMap<Integer, String>();
		ex = new CustomErrorException(0, null);
	}

	@Test
	void updateSettingTest1() {
		settingParam.put(2, "う");
		settingParam.put(6, "文字列テスト");
		settingParam.put(8, "123456");
		settingParam.put(10, "2023-12-31T10:11");
		settingParam.put(12, "2023-12-31");
		settingParam.put(14, "true");
		settingParam.put(16, "う");
		settingService.updateSetting("user2", settingParam);
		settingList = settingRepository.findAllByUsername("user2");
		assertThat(settingList.size() > 0, is(true));
	}

	@Test
	void updateSettingTest2() {
		ex = assertThrows(CustomErrorException.class, () -> settingService.updateSetting("xxxx", settingParam));
		assertThat(ex.getStatusCode(), is(500));
		assertThat(ex.getMessage(), is(util.getMessage("M1000004", new String[] { "xxxx" })));
	}

	@Test
	void updateSettingTest3() {
		settingParam.put(9999, "う");
		settingService.updateSetting("user2", settingParam);
	}

	@Test
	void updateSettingTest4() {
		assertThrows(NullPointerException.class, () -> settingService.updateSetting(null, settingParam));
		assertThrows(NullPointerException.class, () -> settingService.updateSetting("user2", null));

	}
}