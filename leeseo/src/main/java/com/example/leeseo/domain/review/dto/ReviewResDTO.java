package com.example.leeseo.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Schema(name = "ReviewResponse")
    public record JoinDTO(
        Long reviewId,
        LocalDateTime createAt
    ){
    }

    @Builder
    public record ReviewPreViewListDTO(
        List<ReviewPreViewDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ){}


    @Builder
    public record ReviewPreViewDTO(
        String ownerNickname,
        Float score,
        String body,
        LocalDateTime createdAt
    ){}

    @Builder
    public record MyReviewListDTO(
        List<MyReviewDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
    ){}

    @Builder
    public record MyReviewDTO(
        Float rate,
        String content,
        List<String> img_url,
        LocalDateTime createdAt
    ){}
}