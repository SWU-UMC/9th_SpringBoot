package com.example.server_9th.dto;

import lombok.*;

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
    @Builder
    @AllArgsConstructor
    public static class MyReviewResponseDto{
        private Long memberId;
        private String nickName;
        private Double rating;
        private String context;
        private List<String> imageUrl;
        private String reply;
        private LocalDateTime dateTime;
    }
}
