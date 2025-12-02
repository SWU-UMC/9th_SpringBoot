package com.example.umc.domain.review.dto.res;

import lombok.Builder;

@Builder
public record ReviewPreviewDTO(
        Long missionId,
        Long memberId,
        Integer rating,
        String content
) {}
