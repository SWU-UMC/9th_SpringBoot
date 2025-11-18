package com.example.umc.domain.review.service;


import com.example.umc.domain.review.dto.res.ReviewResDTO;
import com.example.umc.domain.review.converter.ReviewConverter;
import com.example.umc.domain.review.dto.req.ReviewReqDTO;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.entity.ReviewPhoto;
import com.example.umc.domain.review.exception.ReviewException;
import com.example.umc.domain.review.exception.code.ReviewErrorCode;
import com.example.umc.domain.review.repository.ReviewPhotoRepository;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.member.exception.MemberException;
import com.example.umc.domain.member.exception.code.MemberErrorCode;
import com.example.umc.domain.member.repository.MemberRepository;
import com.example.umc.domain.store.entity.Store;

import com.example.umc.domain.store.exception.StoreException;
import com.example.umc.domain.store.exception.code.StoreErrorCode;
import com.example.umc.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    private static final Long FIXED_MEMBER_ID = 1L;

    @Transactional
    public ReviewResDTO.CreateDTO createReview(ReviewReqDTO.CreateDTO dto) {

        // 멤버 고정
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 가게 조회
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 평점 멀티 검증
        if (dto.rating() < 0 || dto.rating() > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }

        // 리뷰 저장
        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        // 사진 저장
        if (dto.photoUrls() != null && !dto.photoUrls().isEmpty()) {
            List<ReviewPhoto> photos =
                    ReviewConverter.toReviewPhotoList(dto.photoUrls(), review);
            reviewPhotoRepository.saveAll(photos);
        }

        return ReviewConverter.toCreateDTO(review);
    }
}
