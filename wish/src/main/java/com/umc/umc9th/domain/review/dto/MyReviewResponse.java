package com.umc.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewResponse {

  // 리뷰 기본 정보
  private Integer reviewId;
  private String userName;
  private BigDecimal rating;
  private String content;
  private LocalDateTime createdAt;

  // 가게 정보
  private Integer storeId;
  private String storeName;

  // 답글 정보 (nullable)
  private String replyContent;
  private LocalDateTime replyCreatedAt;
}