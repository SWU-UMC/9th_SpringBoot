package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    /**
     * ReviewRequestDto.CreateReview → Review 엔티티 변환
     */
    public static Review toReview(ReviewRequestDto.CreateReview request, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    /**
     * 사진 URL → ReviewPhoto 엔티티 변환
     */
    public static ReviewPhoto toReviewPhoto(String imageUrl, Review review) {
        return ReviewPhoto.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();
    }

    /**
     * Review 엔티티 → ReviewResponseDto 단일 변환
     */
    public static ReviewResponseDto toDto(Review review) {
        String storeName = review.getStore() != null ? review.getStore().getName() : "Unknown Store"; // 안전한 접근
        return ReviewResponseDto.builder()
                .storeName(storeName)
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    /**
     * Review 리스트 → DTO 리스트 변환
     */
    public static List<ReviewResponseDto> toDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toDto)
                .collect(Collectors.toList());
    }
}