package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String regionName, Integer rate, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if ("region".equals(type) && regionName != null) {
            builder.and(review.store.region.regionName.contains(regionName));
        }

        if ("rate".equals(type) && rate != null) {
            builder.and(review.rate.goe(rate));
        }

        if ("both".equals(type)) {
            if (regionName != null) {
                builder.and(review.store.region.regionName.contains(regionName));
            }
            if (rate != null) {
                builder.and(review.rate.goe(rate));
            }
        }

        return reviewRepository.searchReview(builder);
    }

    public List<Review> findMyReviews(Long memberId, String storeName, Integer rate) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.storeName.containsIgnoreCase(storeName));
        }

        if (rate != null) {
            builder.and(review.rate.eq(rate));
        }

        return reviewRepository.findMyReviews(builder);
    }

    public List<Review> findMyReviewsPaged(Long memberId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 10);
        return reviewRepository.findMyReviewsPaged(memberId, pageable);
    }
}
