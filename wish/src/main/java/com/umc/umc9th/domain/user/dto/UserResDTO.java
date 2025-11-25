package com.umc.umc9th.domain.user.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserResDTO {

  @Builder
  public record JoinDTO(
      UserDTO user
  ) {}

  @Builder
  public record UserDTO(
      Integer user_id,
      String name,
      String gender,
      LocalDate birth_date,
      AddressDTO address,
      String detailed_address,
      List<CategoryDTO> preferred_categories,
      LocalDateTime created_at
  ) {}

  @Builder
  public record AddressDTO(
      Integer address_id,
      String city,
      String district,
      String dong
  ) {}

  @Builder
  public record CategoryDTO(
      Integer category_id,
      String category_type
  ) {}
}