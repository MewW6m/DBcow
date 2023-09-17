package com.dbcow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcow.model.Connect;

import lombok.NonNull;

@Repository
public interface ConnectRepository extends JpaRepository<Connect, Long>  {

    /**
     * 接続情報名とユーザー名から接続情報を取得する
     * @param conname 接続情報名
     * @param username ユーザー情報名
     * @return 接続情報
     */
    Optional<Connect> findByConnameAndUsername(@NonNull String conname, @NonNull String username);
}
