package com.umc.umc9th.domain.user.repository;

import com.umc.umc9th.domain.user.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
