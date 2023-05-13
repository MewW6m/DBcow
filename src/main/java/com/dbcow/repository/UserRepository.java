package com.dbcow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbcow.model.CustomUserDetails;

public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {
    Optional<CustomUserDetails> findByUsername(String username);
}