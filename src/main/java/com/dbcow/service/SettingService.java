package com.dbcow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Setting;
import com.dbcow.repository.SettingRepository;
import com.dbcow.util.RepositoryUtil;
import com.dbcow.util.Util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー情報関連のサービスクラス
 */
@Service
@Slf4j
public class SettingService {

    @Autowired RepositoryUtil repositoryUtil;
    @Autowired SettingRepository settingRepository;
    @Autowired Util util;

    @Transactional(readOnly = false)
    public List<Setting> getSettingList(@NonNull String username) throws CustomErrorException {
        List<Setting> settingList = settingRepository.findAllByUsername(username);
        if (settingList.size() == 0)
            throw new CustomErrorException(500, 
                util.getMessage("M1000004", new String[] { username }));
        return settingList;
    }

    @Transactional(readOnly = false)
    public void updateSetting(@NonNull String username, @NonNull Map<Integer, String> settingParam) 
            throws CustomErrorException {
        List<Setting> settingList = this.getSettingList(username);
        for (Setting setting : settingList)
            setting.setValue(settingParam.get(setting.getId()));
        repositoryUtil.saveAllSetting(settingList);
    }
}