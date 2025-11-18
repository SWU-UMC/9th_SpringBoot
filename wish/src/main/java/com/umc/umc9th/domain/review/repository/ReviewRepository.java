package com.umc.umc9th.domain.review.repository;

import com.umc.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
}