package com.example.server_9th.controller;

import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Review> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Double rating
    ){
        return reviewQueryService.getMyReviews(memberId, storeName, rating);
    }
}
