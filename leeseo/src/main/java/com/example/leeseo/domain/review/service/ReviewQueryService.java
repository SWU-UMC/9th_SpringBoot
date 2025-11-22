package com.example.leeseo.domain.review.service;

import com.example.leeseo.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findReview(
            Long store_id,
            Integer page
    );
}
