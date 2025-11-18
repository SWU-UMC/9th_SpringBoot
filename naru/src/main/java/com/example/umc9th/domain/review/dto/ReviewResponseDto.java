package com.example.umc9th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "리뷰 조회 응답 DTO")
public class ReviewResponseDto {

    @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
    private String storeName;

    @Schema(description = "리뷰 별점 (0.0 ~ 5.0)", example = "4.5")
    private Double score;

    @Schema(description = "리뷰 내용", example = "너무 맛있어요! 재방문 의사 100%")
    private String content;

    @Schema(description = "리뷰 작성 시각", example = "2025-11-04T12:20:33")
    private LocalDateTime createdAt;
}
