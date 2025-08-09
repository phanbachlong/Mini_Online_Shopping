package com.mini.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.shopping.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
