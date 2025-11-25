package com.umc.umc9th.domain.user.converter;

import com.umc.umc9th.domain.address.entity.Address;
import com.umc.umc9th.domain.food.entity.Category;
import com.umc.umc9th.domain.user.dto.UserReqDTO;
import com.umc.umc9th.domain.user.dto.UserResDTO;
import com.umc.umc9th.domain.user.entity.Gender;
import com.umc.umc9th.domain.user.entity.User;

import java.util.List;

public class UserConverter {

  // DTO → Entity
  public static User toUser(UserReqDTO.JoinDTO dto, Address address) {
    return User.builder()
        .name(dto.name())
        .email(dto.email())
        .password(dto.password())
        .gender(Gender.valueOf(dto.gender()))
        .birthDate(dto.birth_date())
        .address(address)
        .detailedAddress(dto.detailed_address())
        .build();
  }

  // Entity → Response DTO
  public static UserResDTO.JoinDTO toJoinDTO(User user) {

    UserResDTO.AddressDTO addressDTO = UserResDTO.AddressDTO.builder()
        .address_id(user.getAddress().getId())
        .city(user.getAddress().getCity())
        .district(user.getAddress().getDistrict())
        .dong(user.getAddress().getDong())
        .build();

    return UserResDTO.JoinDTO.builder()
        .user(
            UserResDTO.UserDTO.builder()
                .user_id(user.getId())
                .name(user.getName())
                .gender(user.getGender().name())
                .birth_date(user.getBirthDate())
                .address(addressDTO)
                .detailed_address(user.getDetailedAddress())
                .preferred_categories(List.of()) // 필요하면 서비스에서 주입
                .created_at(user.getCreatedAt())
                .build()
        )
        .build();
  }
}