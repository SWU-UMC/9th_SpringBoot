package com.example.leeseo.domain.review.controller;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.dto.ReviewReqDTO;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.exception.code.ReviewSuccessCode;
import com.example.leeseo.domain.review.service.ReviewQueryServiceImpl;
import com.example.leeseo.domain.review.service.StoreReviewService;
import com.example.leeseo.global.annotation.PageValid;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs{
    private final ReviewQueryServiceImpl reviewQueryService;
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

    @PostMapping("/store/{storeId}/review")
    public ApiResponse<ReviewResDTO.JoinDTO> saveReview(
            @RequestParam Long memberId,
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, storeReviewService.saveReview(memberId, storeId, dto));
    }

    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam Long storeId,
            @PageValid Integer page
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewQueryService.findReview(storeId, page));
    }

    @GetMapping("/my-reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @PageValid Integer page
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewQueryService.findMyReview(memberId, page));
    }
}
