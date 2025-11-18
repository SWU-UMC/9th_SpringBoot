package com.example.leeseo.domain.review.controller;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public List<QReviewDto> searchReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam(required = false) Long cursorId
    ){
        List<QReviewDto> result = reviewQueryService.searchReview(query,type, cursorId);
        return result;
    }
}
