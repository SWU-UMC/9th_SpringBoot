package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.global.common.dto.SliceResponseDto;

public interface ReviewService {

    ReviewResponseDto createReview(ReviewRequestDto.CreateReview request);
    SliceResponseDto<ReviewResponseDto> getMyReviewList(Long userId, Integer page);
}