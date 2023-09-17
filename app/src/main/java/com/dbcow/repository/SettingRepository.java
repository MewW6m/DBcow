package com.dbcow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcow.model.Setting;

/**
 * 設定情報関連のリポジトリクラス
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
    /**
     * ユーザー名から設定情報の一覧を取得する
     * @param username ユーザー名
     * @return 設定情報一覧
     */
    List<Setting> findAllByUsername(String username);
    
}