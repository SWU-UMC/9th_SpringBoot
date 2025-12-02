package com.example.server_9th.controller;

import com.example.server_9th.apiPayload.ApiResponse;
import com.example.server_9th.apiPayload.valid.ValidPage;
import com.example.server_9th.domain.Member;
import com.example.server_9th.dto.ReviewDto;
import com.example.server_9th.service.ReviewQueryService;
import com.example.server_9th.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.server_9th.apiPayload.code.SuccessCode._OK;


@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;
/*
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Review>>> searchReview(@RequestParam String query, @RequestParam String type){
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query,type);
        return  ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode._OK, result));
    }

    @GetMapping("/myReview")
    public ResponseEntity<ApiResponse<List<ReviewDto.MyReviewResponseDto>>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Double rating
    ){
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode._OK,reviewQueryService.getMyReviews(memberId, storeName, rating)));
    }
 */

    @GetMapping("/me")
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "로그인한 사용자가 작성한 리뷰 목록을 10개씩 페이징하여 조회합니다. " +
                    "page는 1 이상의 정수입니다."
    )
    public ApiResponse<ReviewDto.ReviewPreviewListDto> getMyReviews(
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @ValidPage
            @RequestParam(name = "page") Integer page,

            // 인증 방식에 따라 변경 가능 (userId PathVariable로 받으면 그 방식대로)
            @AuthenticationPrincipal Member loginMember
    ) {

        ReviewDto.ReviewPreviewListDto dto =
                reviewService.getMyReviews(loginMember, page);

        return ApiResponse.onSuccess(_OK, dto);
    }

}
