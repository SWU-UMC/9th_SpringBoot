package com.example.leeseo.domain.review.service;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.exception.code.MemberErrorCode;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.review.converter.ReviewConverter;
import com.example.leeseo.domain.review.dto.QReviewDto;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.entity.QReview;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.exception.ReviewException;
import com.example.leeseo.domain.review.exception.code.ReviewErrorCode;
import com.example.leeseo.domain.review.repository.ReviewRepository;
import com.example.leeseo.domain.store.entity.QStore;
import com.example.leeseo.domain.store.entity.Store;
import com.example.leeseo.domain.store.exception.code.StoreErrorCode;
import com.example.leeseo.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public List<QReviewDto> searchReview(
            String query, String type, Long cursorId
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
        List<QReviewDto> reviewList = reviewRepository.searchReview(builder, cursorId);

        return reviewList;
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            Long store_id,
            Integer page
    ){
        Store store = storeRepository.findById(store_id).orElseThrow(() -> new ReviewException(StoreErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page -1, 10);
        Page<Review> result = reviewRepository.findAllByStore(store,pageRequest);
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    public ReviewResDTO.MyReviewListDTO findMyReview(
            Long member_id,
            Integer page
    ){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new ReviewException(MemberErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page -1, 10);
        Page<Review> result = reviewRepository.findALlByMember(member, pageRequest);
        return ReviewConverter.toMyReviewListDTO(result);
    }
}
