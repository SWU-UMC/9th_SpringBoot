package com.example.leeseo.domain.review.controller;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.dto.ReviewReqDTO;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.exception.code.ReviewSuccessCode;
import com.example.leeseo.domain.review.service.ReviewQueryService;
import com.example.leeseo.domain.review.service.StoreReviewService;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final StoreReviewService storeReviewService;

    @GetMapping("/review/search")
    public List<QReviewDto> searchReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam(required = false) Long cursorId
    ){
        List<QReviewDto> result = reviewQueryService.searchReview(query,type, cursorId);
        return result;
    }

    @PostMapping("/store/{store_id}/addReview")
    public ApiResponse<ReviewResDTO.JoinDTO> saveReview(
            @RequestParam Long member_id,
            @PathVariable Long store_id,
            @Valid @RequestBody ReviewReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, storeReviewService.saveReview(member_id, store_id, dto));
    }
}
