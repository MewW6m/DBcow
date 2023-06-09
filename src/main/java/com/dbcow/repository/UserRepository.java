package com.dbcow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbcow.model.CustomUserDetails;

/**
 * ユーザー情報関連のリポジトリクラス
 */
@Repository
public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {
    /**
     * ユーザー名からユーザー情報を取得する
     * @param username ユーザー名
     * @return ユーザー情報
     */
    Optional<CustomUserDetails> findByUsername(String username);

    /**
     * ユーザー名からユーザー情報を取得する(削除フラグ考慮しない)
     * @param username ユーザー名
     * @return ユーザー情報(削除フラグ考慮しない)
     */
    @Query(value="SELECT * FROM user WHERE username = :username", nativeQuery=true)
    Optional<CustomUserDetails> findByUsernameNoDeleteFlag(@Param("username") String username);
}