package com.umc.umc9th.domain.user.repository;

import com.umc.umc9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  // user_id로 특정 유저 조회 (마이페이지용)
  Optional<User> findById(Integer userId);
}