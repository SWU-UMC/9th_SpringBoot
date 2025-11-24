package com.example.umc9th.domain.review.dto;

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

    private String replyText; // 사장님 답글(없으면 null)
    private LocalDateTime createdAt;
}
