package com.umc.umc9th.domain.food.repository;

import com.umc.umc9th.domain.food.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}