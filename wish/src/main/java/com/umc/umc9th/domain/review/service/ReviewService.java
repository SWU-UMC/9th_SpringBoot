package com.umc.umc9th.domain.review.service;

import com.umc.umc9th.domain.image.entity.Image;
import com.umc.umc9th.domain.image.repository.ImageRepository;
import com.umc.umc9th.domain.review.converter.ReviewConverter;
import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import com.umc.umc9th.domain.review.dto.ReviewReqDTO;
import com.umc.umc9th.domain.review.dto.ReviewResDTO;
import com.umc.umc9th.domain.review.entity.Review;
import com.umc.umc9th.domain.review.exception.ReviewErrorCode;
import com.umc.umc9th.domain.review.exception.ReviewException;
import com.umc.umc9th.domain.review.repository.ReviewRepository;
import com.umc.umc9th.domain.store.entity.Store;
import com.umc.umc9th.domain.store.repository.StoreRepository;
import com.umc.umc9th.domain.user.entity.User;
import com.umc.umc9th.domain.user.repository.UserRepository;
import java.util.List;
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
  private final ImageRepository imageRepository;
  private final UserRepository userRepository;
  private final StoreRepository storeRepository;

  /**
   * 리뷰 조회
   */
  @Transactional(readOnly = true)
  public ReviewResDTO.MyReviewListDTO getMyReviews(
      Integer userId,
      Integer storeId,
      BigDecimal minRating,
      BigDecimal maxRating,
      Pageable pageable
  ) {
    if (userId == null) {
      throw new IllegalArgumentException("사용자 ID는 필수입니다.");
    }

    validateRatingRange(minRating, maxRating);

    Page<MyReviewResponse> page = reviewRepository.findMyReviews(
        userId, storeId, minRating, maxRating, pageable
    );

    return ReviewConverter.toMyReviewListDTO(page);
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

  /**
   * 리뷰 생성
   */
  @Transactional
  public ReviewResDTO.CreateDTO createReview(Integer userId, ReviewReqDTO.CreateDTO dto) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND));

    Store store = storeRepository.findById(dto.store_id())
        .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

    Review review = ReviewConverter.toReview(dto, user, store);
    reviewRepository.save(review);

    // 이미지 저장
    List<Image> images = dto.image_urls().stream()
        .map(url -> Image.builder()
            .imageUrl(url)
            .target("REVIEW")
            .targetId(review.getId())
            .build())
        .toList();

    imageRepository.saveAll(images);

    return ReviewConverter.toCreateDTO(review, images);
  }
}