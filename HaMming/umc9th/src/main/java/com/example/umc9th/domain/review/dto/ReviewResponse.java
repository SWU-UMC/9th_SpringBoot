package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {

    private Long reviewId;
    private String reviewText;
    private Integer rate;

    private Long storeId;
    private String storeName;
    private String regionName;

    private String replyText;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .reviewText(review.getReviewText())
                .rate(review.getRate())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getStoreName())
                .regionName(review.getStore().getRegion().getRegionName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
