package com.example.umc.domain.review.dto.req;

import java.util.List;

public class ReviewReqDTO {

    public record CreateDTO(
            Long storeId,
            Integer rating,
            String content,
            List<String> photoUrls
    ) {}
}
