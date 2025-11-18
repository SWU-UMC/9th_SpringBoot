package com.umc.umc9th.domain.review.service;

import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import com.umc.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public Page<MyReviewResponse> getMyReviews(Integer userId, Integer storeId,
      BigDecimal minRating, BigDecimal maxRating,
      Pageable pageable) {
    if (userId == null) {
      throw new IllegalArgumentException("사용자 ID는 필수입니다.");
    }

    validateRatingRange(minRating, maxRating);

    return reviewRepository.findMyReviews(userId, storeId, minRating, maxRating, pageable);
  }

  private void validateRatingRange(BigDecimal minRating, BigDecimal maxRating) {
    if (minRating != null && (minRating.compareTo(BigDecimal.ZERO) < 0 ||
        minRating.compareTo(BigDecimal.valueOf(5)) > 0)) {
      throw new IllegalArgumentException("최소 별점은 0.0에서 5.0 사이여야 합니다.");
    }

    if (maxRating != null && (maxRating.compareTo(BigDecimal.ZERO) < 0 ||
        maxRating.compareTo(BigDecimal.valueOf(5)) > 0)) {
      throw new IllegalArgumentException("최대 별점은 0.0에서 5.0 사이여야 합니다.");
    }

    if (minRating != null && maxRating != null && minRating.compareTo(maxRating) > 0) {
      throw new IllegalArgumentException("최소 별점이 최대 별점보다 클 수 없습니다.");
    }
  }
}