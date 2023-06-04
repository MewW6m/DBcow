package com.dbcow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbcow.model.CustomUserDetails;

/**
 * ユーザー情報関連のリポジトリクラス
 */
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

    /**
     * ユーザー情報一覧を取得する(削除フラグ考慮しない)
     * @param username ユーザー名
     * @return ユーザー情報(削除フラグ考慮しない)
     */
    @Query(value="SELECT * FROM user", nativeQuery=true)
    List<CustomUserDetails> findAllNoDeleteFlag();
}