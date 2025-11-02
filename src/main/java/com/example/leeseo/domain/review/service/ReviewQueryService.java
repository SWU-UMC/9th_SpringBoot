package com.example.leeseo.domain.review.service;

import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.entity.QReview;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.repository.ReviewRepository;
import com.example.leeseo.domain.store.entity.QLocation;
import com.example.leeseo.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<QReviewDto> searchReview(
            String query, String type
    ){
        //Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //동적 쿼리: 검색 조건
        if (type.equals("store")){
            builder.and(review.store.id.eq(parseLong(query)));
        }
        if (type.equals("rate")){
            int rate_avg = Integer.parseInt(query);
            builder.and(review.rate.goe(rate_avg));
            builder.and(review.rate.lt(rate_avg + 1));
        }
        if (type.equals("both")){
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(store.id.eq(parseLong(firstQuery)));

            int rate_avg = Integer.parseInt(secondQuery);
            builder.and(review.rate.goe(rate_avg));
            builder.and(review.rate.lt(rate_avg + 1));
        }

        //Repository 사용 & 결과 매핑
        List<QReviewDto> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }
}
