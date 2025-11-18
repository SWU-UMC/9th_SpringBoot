package com.example.umc.domain.review.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QReviewDto {
    private Long reviewId;
    private LocalDateTime createdAt;
    private String content;
    private Integer rating;
    private String storeName;
    private String memberName;

    public QReviewDto(Long reviewId, LocalDateTime createdAt, String content,
                      Integer rating, String storeName, String memberName) {
        this.reviewId = reviewId;
        this.createdAt = createdAt;
        this.content = content;
        this.rating = rating;
        this.storeName = storeName;
        this.memberName = memberName;
    }
}


