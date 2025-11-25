package com.example.server_9th.service.mission;

import com.example.server_9th.domain.Member;
import com.example.server_9th.dto.MissionDto;

public interface MissionService {

    MissionDto.MissionPreviewListDto getMyInProgressMissions(Member loginMember, Integer page);
}
