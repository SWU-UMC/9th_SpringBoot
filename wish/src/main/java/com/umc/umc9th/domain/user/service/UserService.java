package com.umc.umc9th.domain.user.service;

import com.umc.umc9th.domain.user.dto.UserReqDTO;
import com.umc.umc9th.domain.user.dto.UserResDTO;

public interface UserService {
  UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto);
}
