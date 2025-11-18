package com.umc.umc9th.domain.review.repository;

import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

public interface ReviewRepositoryCustom {

  Page<MyReviewResponse> findMyReviews(
      Integer userId,
      Integer storeId,
      BigDecimal minRating,
      BigDecimal maxRating,
      Pageable pageable
  );
}