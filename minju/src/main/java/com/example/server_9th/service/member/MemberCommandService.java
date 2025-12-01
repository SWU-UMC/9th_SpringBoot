package com.example.server_9th.service.member;

import com.example.server_9th.dto.MemberReqDTO;
import com.example.server_9th.dto.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO request);

    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);

}
