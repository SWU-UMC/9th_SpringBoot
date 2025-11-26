package com.example.leeseo.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    @Schema(name = "ReviewResponse")
    public record JoinDTO(
        Long reviewId,
        LocalDateTime createAt
    ){
    }
}