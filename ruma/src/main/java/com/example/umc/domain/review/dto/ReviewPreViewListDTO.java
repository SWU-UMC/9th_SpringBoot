package com.example.umc.domain.review.dto;

import lombok.Builder;

import java.util.List;


@Builder
public record ReviewPreViewListDTO (
        List<ReviewPreViewListDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElement,
        Boolean isFirst,
        Boolean isLast
){ }
