package com.dbcow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbcow.model.CustomUserDetails;

import lombok.NonNull;

public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {
    Optional<CustomUserDetails> findByUsername(@NonNull String username);

    Optional<CustomUserDetails> findById(Long id);
}