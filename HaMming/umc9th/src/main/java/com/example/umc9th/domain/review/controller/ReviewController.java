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

    @GetMapping("/search")
    public List<ReviewResponse> searchReview(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Integer rate,
            @RequestParam(defaultValue = "both") String type
    ) {
        List<Review> reviews = reviewQueryService.searchReview(region, rate, type);

        // 엔티티 대신 DTO로 변환
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
