package com.example.leeseo.domain.member.service;

import com.example.leeseo.domain.member.dto.MemberReqDTO;
import com.example.leeseo.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto);
}
