package com.example.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDto {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewRequest {
        private Long memberId;
        private Long storeId;
        private String reviewText;
        private Integer rate;
    }
}
