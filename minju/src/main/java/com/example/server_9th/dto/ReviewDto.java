package com.example.server_9th.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewRequestDto{
        private String context;
        private Double rating;
        private List<String> imageUrl;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MyReviewResponseDto{
        private Long memberId;
        private String nickName;
        private String storeName;
        private Double rating;
        private String context;
        private List<String> imageUrl;
        private String reply;
        private LocalDateTime dateTime;
    }

    //리뷰 리스트에 사용될 Preview DTO
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreviewDto {
        private String nickname;
        private Double rating;
        private String context;
        private List<String> imageUrls;
        private LocalDate createdAt;
    }


    //리스트 + 페이징 정보 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreviewListDto {
        private List<ReviewPreviewDto> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
