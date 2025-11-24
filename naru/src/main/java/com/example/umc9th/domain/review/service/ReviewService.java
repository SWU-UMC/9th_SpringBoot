package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;

public interface ReviewService {

    ReviewResponseDto createReview(ReviewRequestDto.CreateReview request);
}