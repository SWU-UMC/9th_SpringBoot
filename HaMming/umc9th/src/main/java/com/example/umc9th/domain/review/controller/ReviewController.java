package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewCreateResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    /**
     * 1. 리뷰 검색 API
     *  - region (옵션)
     *  - rate   (옵션)
     *  - type = region / rate / both
     */
    @GetMapping("/search")
    public ApiResponse<List<ReviewResponse>> searchReview(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Integer rate,
            @RequestParam(defaultValue = "both") String type
    ) {
        List<Review> reviews = reviewQueryService.searchReview(region, rate, type);
        List<ReviewResponse> result = reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }

    /**
     * 2. 내가 작성한 리뷰 보기
     *  - memberId (필수)
     *  - storeName, rate (옵션)
     */
    @GetMapping("/my")
    public ApiResponse<List<ReviewResponse>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rate
    ) {
        List<Review> reviews = reviewQueryService.findMyReviews(memberId, storeName, rate);
        List<ReviewResponse> result = reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                result
        );
    }
    @PostMapping
    public ApiResponse<ReviewCreateResponse> createReview(
            @RequestBody ReviewRequestDto.CreateReviewRequest req
    ) {

        Review review = reviewCommandService.createReview(req);

        ReviewCreateResponse result = ReviewCreateResponse.builder()
                .reviewId(review.getId())
                .build();

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                result
        );
    }

}
