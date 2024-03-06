package com.example.TestSecurity.repository;

import com.example.TestSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<UserEntity, Long> {
}
