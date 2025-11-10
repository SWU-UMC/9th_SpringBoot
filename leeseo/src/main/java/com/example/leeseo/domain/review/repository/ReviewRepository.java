package com.example.leeseo.domain.review.repository;

import com.example.leeseo.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
}
