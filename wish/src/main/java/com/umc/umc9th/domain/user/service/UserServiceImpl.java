package com.umc.umc9th.domain.user.service;

import com.umc.umc9th.domain.address.entity.Address;
import com.umc.umc9th.domain.address.repository.AddressRepository;
import com.umc.umc9th.domain.food.entity.Category;
import com.umc.umc9th.domain.food.repository.CategoryRepository;
import com.umc.umc9th.domain.user.converter.UserConverter;
import com.umc.umc9th.domain.user.dto.UserReqDTO;
import com.umc.umc9th.domain.user.dto.UserResDTO;
import com.umc.umc9th.domain.user.entity.User;
import com.umc.umc9th.domain.user.entity.UserCategory;
import com.umc.umc9th.domain.user.exception.UserErrorCode;
import com.umc.umc9th.domain.user.exception.UserException;
import com.umc.umc9th.domain.user.repository.UserCategoryRepository;
import com.umc.umc9th.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final CategoryRepository categoryRepository;
  private final UserCategoryRepository userCategoryRepository;

  @Override
  @Transactional
  public UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto) {

    // 이메일 중복 체크
    if (userRepository.existsByEmail(dto.email())) {
      throw new UserException(UserErrorCode.DUPLICATED_EMAIL);
    }

    // 주소 조회
    Address address = addressRepository
        .findByCityAndDistrictAndDong(
            dto.address().city(),
            dto.address().district(),
            dto.address().dong()
        )
        .orElseThrow(() -> new UserException(UserErrorCode.ADDRESS_NOT_FOUND));

    // User 생성
    User user = UserConverter.toUser(dto, address);
    userRepository.save(user);

    // 선호 카테고리 처리
    if (dto.preferred_categories() != null && !dto.preferred_categories().isEmpty()) {

      List<UserCategory> categories = dto.preferred_categories().stream()
          .map(id -> {

            Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new UserException(UserErrorCode.CATEGORY_NOT_FOUND));

            return UserCategory.builder()
                .user(user)
                .category(category)
                .build();
          })
          .toList();

      userCategoryRepository.saveAll(categories);
    }

    return UserConverter.toJoinDTO(user);
  }
}
