package com.example.leeseo.domain.review.service;

import com.example.leeseo.domain.member.entity.Member;
import com.example.leeseo.domain.member.exception.code.MemberErrorCode;
import com.example.leeseo.domain.member.repository.MemberRepository;
import com.example.leeseo.domain.review.converter.ReviewConverter;
import com.example.leeseo.domain.review.dto.ReviewReqDTO;
import com.example.leeseo.domain.review.dto.ReviewResDTO;
import com.example.leeseo.domain.review.entity.Review;
import com.example.leeseo.domain.review.exception.ReviewException;
import com.example.leeseo.domain.review.exception.code.ReviewErrorCode;
import com.example.leeseo.domain.review.repository.ReviewRepository;
import com.example.leeseo.domain.store.entity.Store;
import com.example.leeseo.domain.store.exception.StoreErrorCode;
import com.example.leeseo.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReviewService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResDTO.JoinDTO saveReview(
            Long member_id,
            Long store_id,
            ReviewReqDTO.JoinDTO dto
    ){
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new ReviewException(MemberErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(store_id)
                .orElseThrow(() -> new ReviewException(StoreErrorCode.NOT_FOUND));
        Review review = ReviewConverter.toReview(dto, member, store);

        reviewRepository.save(review);

        return ReviewConverter.toJoinDTO(review);
    }
}
