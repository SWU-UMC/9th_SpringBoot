package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private String reviewText;
    private Integer rate;
    private String memberName;
    private String storeName;
    private String regionName;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewText(review.getReviewText())
                .rate(review.getRate())
                .memberName(review.getMember().getNickname()) // Member의 LAZY 프록시 깨뜨려서 실 데이터 가져옴
                .storeName(review.getStore().getStoreName())
                .regionName(review.getStore().getRegion().getRegionName())
                .build();
    }
}
