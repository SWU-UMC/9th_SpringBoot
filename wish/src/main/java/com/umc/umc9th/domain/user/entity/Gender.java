package com.umc.umc9th.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
  MALE("남"),
  FEMALE("여"),
  NONE("선택안함");

  private final String description;
}
