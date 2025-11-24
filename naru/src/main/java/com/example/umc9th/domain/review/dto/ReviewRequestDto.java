package com.example.umc9th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

@Schema(description = "리뷰 요청 DTO")
public class ReviewRequestDto {

    @Getter
    @Schema(description = "리뷰 등록 요청 DTO")
    public static class CreateReview {

        @NotNull(message = "사용자 ID는 필수입니다.")
        @Schema(description = "리뷰 작성 사용자 ID", example = "1")
        private Long userId;

        @NotNull(message = "가게 ID는 필수입니다.")
        @Schema(description = "리뷰 대상 가게 ID", example = "10")
        private Long storeId;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
        @Schema(description = "리뷰 별점 (0.0 ~ 5.0)", example = "4.5")
        private Double score;

        @Size(max = 1000, message = "내용은 1000자를 초과할 수 없습니다.")
        @Schema(description = "리뷰 내용", example = "너무 맛있어요! 재방문 의사 100%", required = false)
        private String content;

        @Schema(description = "리뷰 사진 URL 리스트 (선택 사항)", example = "['https://photo.url/1.jpg', 'https://photo.url/2.jpg']", required = false)
        private List<String> photoUrls;
    }
}