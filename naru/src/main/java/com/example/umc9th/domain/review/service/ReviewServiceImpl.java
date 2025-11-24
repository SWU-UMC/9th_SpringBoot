package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.error.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.error.UserErrorCode;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.common.dto.SliceResponseDto;
import com.example.umc9th.global.entity.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService { // ReviewService 구현

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResponseDto createReview(ReviewRequestDto.CreateReview request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GeneralException(StoreErrorCode.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, user, store);

        // 사진 정보가 있다면 ReviewPhoto 엔티티를 생성하여 Review에 추가
        if (request.getPhotoUrls() != null && !request.getPhotoUrls().isEmpty()) {
            request.getPhotoUrls().forEach(url -> {
                newReview.addPhoto(ReviewConverter.toReviewPhoto(url, newReview));
            });
        }

        // Review 저장
        Review savedReview = reviewRepository.save(newReview);

        // 저장된 Review 엔티티를 DTO로 변환하여 반환
        return ReviewConverter.toDto(savedReview);
    }

    @Override
    public SliceResponseDto<ReviewResponseDto> getMyReviewList(Long userId, Integer page) {
        // 유저 검증
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(UserErrorCode.USER_NOT_FOUND));

        // PageRequest 생성 (0-based 인덱스 변환: page - 1)
        // 사이즈 10, 최신순(createdAt DESC) 정렬
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());

        // Repository 조회 (Slice 반환)
        Slice<Review> reviewSlice = reviewRepository.findAllByUserId(user.getId(), pageRequest);

        // 변환 후 반환
        return ReviewConverter.toReviewPageDto(reviewSlice);
    }
}