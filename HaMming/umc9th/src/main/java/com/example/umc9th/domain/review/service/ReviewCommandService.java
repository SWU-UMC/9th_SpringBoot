package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.entity.Review;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDto.CreateReviewRequest req);
}
