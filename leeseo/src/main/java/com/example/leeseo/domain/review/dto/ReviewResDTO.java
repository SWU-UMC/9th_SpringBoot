package com.example.leeseo.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record JoinDTO(
        Long reviewId,
        LocalDateTime createAt
    ){
    }
}