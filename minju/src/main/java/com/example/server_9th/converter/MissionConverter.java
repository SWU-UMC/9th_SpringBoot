package com.example.server_9th.converter;

import com.example.server_9th.domain.mapping.myMission.MyMission;
import com.example.server_9th.dto.MissionDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MissionConverter {
    public static MissionDto.MissionPreviewDto toMissionPreviewDto(MyMission myMission) {
        return MissionDto.MissionPreviewDto.builder()
                .missionId(myMission.getMyMission_id().getMission_id())
                .storeName(myMission.getMission().getStore().getStoreName())
                .title(myMission.getMission().getName())
                .description(myMission.getMission().getContent())
                .assignedDate(LocalDate.from(myMission.getCreatedAt()))
                .status(myMission.getMissionStatus().name())
                .build();
    }



    public static MissionDto.MissionPreviewListDto toMissionPreviewListDto(Page<MyMission> missionPage) {

        return MissionDto.MissionPreviewListDto.builder()
                .missionList(
                        missionPage.getContent().stream()
                                .map(MissionConverter::toMissionPreviewDto)
                                .toList()
                )
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
