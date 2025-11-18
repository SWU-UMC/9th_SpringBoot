package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 프로필 조회 쿼리

    // 1) 메서드 이름 기반 쿼리
    @NonNull
    Optional<User> findById(Long id);

    // 2) @Query 기반 쿼리
    @Query(value = """
        SELECT
            u.name         AS name,
            u.email        AS email,
            u.phone_number AS phoneNumber,
            u.point        AS point
        FROM user u
        WHERE u.id = :userId
        """, nativeQuery = true)
    Optional<User> findUserBasicInfoById(@Param("userId") Long userId);

}
