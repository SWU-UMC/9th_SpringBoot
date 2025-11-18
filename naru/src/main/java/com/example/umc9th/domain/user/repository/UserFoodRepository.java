package com.example.umc9th.domain.user.repository;

import com.example.umc9th.domain.user.entity.mapping.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {

}
