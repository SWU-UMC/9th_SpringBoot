package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 기존 검색
    @GetMapping("/search")
    public List<ReviewResponse> searchReview(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Integer rate,
            @RequestParam(defaultValue = "both") String type
    ) {
        List<Review> reviews = reviewQueryService.searchReview(region, rate, type);
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }

    // 내가 작성한 리뷰 보기
    @GetMapping("/my")
    public List<ReviewResponse> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rate
    ) {
        List<Review> reviews = reviewQueryService.findMyReviews(memberId, storeName, rate);
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
