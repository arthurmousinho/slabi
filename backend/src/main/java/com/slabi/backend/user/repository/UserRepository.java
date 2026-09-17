package com.slabi.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slabi.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
