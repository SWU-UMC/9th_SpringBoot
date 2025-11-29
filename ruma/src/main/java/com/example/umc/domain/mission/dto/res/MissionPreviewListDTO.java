package com.example.umc.domain.mission.dto.res;

import com.example.umc.domain.review.dto.res.ReviewPreviewDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record MissionPreviewListDTO (
    List<MissionPreviewDTO> missionList,
    Integer listSize,
    Integer totalPage,
    Long totalElement,
    Boolean isFirst,
    Boolean isLast
){}
