package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    @Override
    public Review createReview(ReviewRequestDto.CreateReviewRequest req) {
//
//        Member member = memberRepository.findById(req.getMemberId())
//                .orElseThrow(() -> new GeneralException(BaseErrorCode));



        Review review = Review.builder()
//                .member(member)
                .reviewText(req.getReviewText())
                .rate(req.getRate())
                .build();

        return reviewRepository.save(review);
    }
}
