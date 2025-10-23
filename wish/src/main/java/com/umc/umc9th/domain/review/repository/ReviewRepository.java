package com.umc.umc9th.domain.review.repository;

import com.umc.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

  // 리뷰 ID로 리뷰 조회 (작성자 포함)
  Optional<Review> findById(Integer reviewId);

  // 특정 가게(store_id)의 모든 리뷰 조회 (작성자 포함)
  List<Review> findAllByStore_Id(Integer storeId);
  }