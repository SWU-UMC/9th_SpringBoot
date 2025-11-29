package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // 특정 유저의 리뷰 목록 조회 (Slice 반환)
    Slice<Review> findAllByUserId(Long userId, Pageable pageable);

}