package com.example.server_9th.service;


import com.example.server_9th.domain.QRegion;
import com.example.server_9th.domain.QStore;
import com.example.server_9th.domain.mapping.review.QReview;
import com.example.server_9th.domain.mapping.review.Review;
import com.example.server_9th.repository.ReviewRepo.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type){

        //Q클래스 정의
        QReview review = QReview.review;
        QRegion region = QRegion.region;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if (type.equals("region")) {
            builder.and(region.dong.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.rating.goe(Double.parseDouble(query)));
        }
        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];
            builder.and(region.dong.contains(firstQuery));
            builder.and(review.rating.goe(Double.parseDouble(secondQuery)));
        }

        return reviewRepository.searchReview(builder);
    }

    //내가 작성한 리뷰 보기
    public List<Review> getMyReviews(Long memberId, String storeName, Double rating){

        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        // 내가 작성한 리뷰만
        builder.and(review.id.user_id.eq(memberId));

        if (storeName != null && !storeName.isEmpty()){
            builder.and(store.storeName.eq(storeName));
        }

        if (rating != null){
            double min = rating.doubleValue();
            double max = min + 0.9;
            builder.and(review.rating.between(min, max));
        }

        return reviewRepository.searchMyReviews(builder);
    }
}
