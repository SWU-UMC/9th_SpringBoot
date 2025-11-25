package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.QReviewDto;
import com.example.umc.domain.review.dto.res.ReviewPreViewListDTO;
import com.example.umc.domain.review.dto.res.ReviewPreviewDTO;
import com.example.umc.domain.review.entity.QReview;
import com.example.umc.domain.mission.entity.QMission;
import com.example.umc.domain.review.repository.ReviewQueryDslImpl;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewQueryDslImpl reviewQueryDSL;

    //내 리뷰 조회
    public List<QReviewDto> getMyReviews(Long memberId, Long storeId, Integer starRange, Long cursorId) {
        QReview review = QReview.review;
        QMission mission = QMission.mission;

        BooleanBuilder condition = new BooleanBuilder();
        if (memberId != null) condition.and(review.member.memberId.eq(memberId));
        if (storeId != null) condition.and(mission.store.storeId.eq(storeId));
        if (starRange != null) {
            condition.and(review.rating.eq(starRange));
        }
        return reviewQueryDSL.findMyReviews(condition, cursorId);
    }

    //검색 기능 (가게명/별점/둘다)
    public List<QReviewDto> searchReviews(String query, String type) {
        switch (type.toLowerCase()) {
            case "store":
                return reviewQueryDSL.findByStoreName(query);
            case "rate":
                return reviewQueryDSL.findByRating(Integer.parseInt(query));
            case "both":
                return reviewQueryDSL.findByStoreOrRating(query);
            default:
                throw new IllegalArgumentException("Invalid type parameter: " + type);
        }
    }

    //storeId 기반 리뷰 조회 (페이징 포함)
    public ReviewPreViewListDTO getStoreReviewList(Long storeId, Integer page) {

        int pageSize = 10;

        // 1) 리뷰 목록 조회 (QueryDSL)
        List<QReviewDto> reviews =
                reviewQueryDSL.findStoreReviews(storeId, page, pageSize);

        // 2) 전체 리뷰 수 조회
        long totalCount =
                reviewQueryDSL.countStoreReviews(storeId);

        // 3) ReviewPreviewDTO 로 변환
        List<ReviewPreviewDTO> previewList =
                reviews.stream()
                        .map(r -> ReviewPreviewDTO.builder()
                                .reviewId(r.getReviewId())
                                .memberId(r.getMemberId())
                                .rating(r.getRating())
                                .content(r.getContent())
                                .build())
                        .toList();

        // 4) ReviewPreViewListDTO 생성
        return ReviewPreViewListDTO.builder()
                .reviewList(previewList)
                .listSize(pageSize)
                .totalPage((int) Math.ceil((double) totalCount / pageSize))
                .totalElement(totalCount)  // ⭐ record 필드명에 맞춤 (totalElements 아님!)
                .isFirst(page == 0)
                .isLast((page + 1) * pageSize >= totalCount)
                .build();
    }

}
