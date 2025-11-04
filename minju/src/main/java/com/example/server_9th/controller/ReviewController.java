package com.example.server_9th.controller;

import com.example.server_9th.converter.ReviewConverter;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.dto.ReviewDto;
import com.example.server_9th.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query,type);
        return  result;
    }

    @GetMapping("/myReview")
    public List<ReviewDto.MyReviewResponseDto> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Double rating
    ){
        List<Review> reviews = reviewQueryService.getMyReviews(memberId, storeName, rating);

        return reviews.stream()
                .map(ReviewConverter::toMyReviewResponseDto)
                .collect(Collectors.toList());
    }
}
