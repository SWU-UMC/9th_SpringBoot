package com.example.umc.domain.review.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewPreViewListDTO(
        List<ReviewPreviewDTO> reviewList,
        Integer listSize,
        Integer totalPage,
        Long totalElement,
        Boolean isFirst,
        Boolean isLast
) { }
