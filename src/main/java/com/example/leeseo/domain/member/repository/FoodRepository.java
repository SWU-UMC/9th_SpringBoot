package com.example.leeseo.domain.member.repository;

import com.example.leeseo.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
