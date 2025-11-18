package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.QReviewDto;
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

    // 회원 리뷰 조회
    public List<QReviewDto> getMyReviews(Long memberId, Long storeId, Integer starRange, Long cursorId) {
        QReview review = QReview.review;
        QMission mission = QMission.mission;

        BooleanBuilder condition = new BooleanBuilder();
        if (memberId != null) condition.and(review.member.memberId.eq(memberId));
        if (storeId != null) condition.and(mission.store.storeId.eq(storeId));
        if (starRange != null) {
            int min = starRange;
            int max = starRange + 0; // 별점 범위 조정 시 여기 수정
            condition.and(review.rating.between(min, max));
        }
        return reviewQueryDSL.findMyReviews(condition, cursorId);
    }

    // 검색 (store, rate, both)
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
}

