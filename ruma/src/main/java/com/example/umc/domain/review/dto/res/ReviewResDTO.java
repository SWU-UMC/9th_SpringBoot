package com.example.umc.domain.review.dto.res;

import lombok.Builder;
import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateDTO(
            Long reviewId,
            Long storeId,
            Long memberId,
            Integer rating,
            String content,
            LocalDateTime createdAt
    ) {}
}
