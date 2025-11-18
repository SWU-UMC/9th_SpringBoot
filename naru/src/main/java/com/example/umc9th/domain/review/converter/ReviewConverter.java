package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    /**
     * Review 엔티티 → ReviewResponseDto 단일 변환
     */
    public static ReviewResponseDto toDto(Review review) {
        return ReviewResponseDto.builder()
                .storeName(review.getStore().getName())
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
