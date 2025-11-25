package com.example.umc.domain.review.dto.res;

import lombok.Builder;

@Builder
public record ReviewPreviewDTO(
        Long reviewId,
        Long memberId,
        Integer rating,
        String content
) {}
