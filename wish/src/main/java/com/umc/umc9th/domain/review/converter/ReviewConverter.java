package com.umc.umc9th.domain.review.converter;

import com.umc.umc9th.domain.image.entity.Image;
import com.umc.umc9th.domain.review.dto.MyReviewResponse;
import com.umc.umc9th.domain.review.dto.ReviewReqDTO;
import com.umc.umc9th.domain.review.dto.ReviewResDTO;
import com.umc.umc9th.domain.review.entity.Review;
import com.umc.umc9th.domain.store.entity.Store;
import com.umc.umc9th.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;

public class ReviewConverter {

  // DTO → Review 엔티티
  public static Review toReview(
      ReviewReqDTO.CreateDTO dto,
      User user,
      Store store
  ){
    return Review.builder()
        .user(user)
        .store(store)
        .rating(dto.rating())
        .content(dto.content())
        .build();
  }


  // Image 엔티티 리스트 → Response DTO의 images
  public static List<ReviewResDTO.ImageDTO> toImageDTOList(List<Image> images) {
    return images.stream()
        .map(img -> ReviewResDTO.ImageDTO.builder()
            .image_id(img.getId())
            .image_url(img.getImageUrl())
            .build()
        )
        .toList();
  }


  // Review 엔티티 + 하위 정보 → 응답 DTO
  public static ReviewResDTO.CreateDTO toCreateDTO(
      Review review,
      List<Image> images
  ){
    return ReviewResDTO.CreateDTO.builder()
        .review(
            ReviewResDTO.ReviewDTO.builder()
                .review_id(review.getId())
                .user(
                    ReviewResDTO.UserDTO.builder()
                        .user_id(review.getUser().getId())
                        .name(review.getUser().getName())
                        .build()
                )
                .store(
                    ReviewResDTO.StoreDTO.builder()
                        .store_id(review.getStore().getId())
                        .store_name(review.getStore().getStoreName())
                        .build()
                )
                .rating(review.getRating())
                .content(review.getContent())
                .images(toImageDTOList(images))
                .created_at(review.getCreatedAt())
                .build()
        )
        .build();
  }

  public static ReviewResDTO.MyReviewListDTO toMyReviewListDTO(Page<MyReviewResponse> page) {
    return ReviewResDTO.MyReviewListDTO.builder()
        .reviews(page.getContent())
        .listSize(page.getNumberOfElements())
        .totalPage(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .isFirst(page.isFirst())
        .isLast(page.isLast())
        .build();
  }
}
