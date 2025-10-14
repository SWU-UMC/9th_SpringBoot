package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository, MemberRepository memberRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.storeRepository = storeRepository;
    }

    public Review insert(Long member_id, Long store_id, String content, Float rate){
        Member member = memberRepository.getReferenceById(member_id);
        Store store = storeRepository.getReferenceById(store_id);

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(content)
                .rate(rate)
                .build();

        return reviewRepository.save(review);
    }
}
