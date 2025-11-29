package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.mapping.UserMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.common.dto.SliceResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionConverter {

    /**
     * User와 Mission 엔티티를 UserMission 엔티티로 변환
     */
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .build();
    }

    /**
     * UserMission 엔티티 → MissionResponseDto.ChallengeResult DTO 변환
     */
    public static MissionResponseDto.ChallengeResult toChallengeResultDto(UserMission userMission) {
        return MissionResponseDto.ChallengeResult.builder()
                .userMissionId(userMission.getId())
                .build();
    }

    /**
     * Mission 엔티티 → MissionPreviewDto 변환
     */
    public static MissionResponseDto.MissionPreviewDto toMissionPreviewDto(Mission mission) {
        return MissionResponseDto.MissionPreviewDto.builder()
                .id(mission.getId())
                .point(mission.getPoint())
                .condition(mission.getCondition())
                .deadline(mission.getDeadline().toLocalDate())
                .build();
    }

    /**
     * 무한 스크롤용 Mission 리스트 → DTO 리스트 변환
     */
    public static SliceResponseDto<MissionResponseDto.MissionPreviewDto> toMissionPreviewList(Slice<Mission> missionSlice) {
        List<MissionResponseDto.MissionPreviewDto> missionDtoList = missionSlice.getContent().stream()
                .map(MissionConverter::toMissionPreviewDto)
                .collect(Collectors.toList());

        return SliceResponseDto.<MissionResponseDto.MissionPreviewDto>builder()
                .list(missionDtoList)
                .listSize(missionDtoList.size())
                .hasNext(missionSlice.hasNext())
                .isFirst(missionSlice.isFirst())
                .isLast(missionSlice.isLast())
                .build();
    }

    /**
     * Mission 엔티티 → MyMissionDto 변환
     */
    public static MissionResponseDto.MyMissionDto toMyMissionDto(UserMission userMission) {
        return MissionResponseDto.MyMissionDto.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getMission().getStore().getName())
                .point(userMission.getMission().getPoint())
                .status(userMission.getStatus())
                .condition(userMission.getMission().getCondition())
                .deadline(userMission.getMission().getDeadline().toLocalDate())
                .build();
    }

    /**
     * 무한 스크롤용 참여 중인 Mission 리스트 → DTO 리스트 변환
     */
    public static SliceResponseDto<MissionResponseDto.MyMissionDto> toMyMissionList(Slice<UserMission> userMissionSlice) {
        List<MissionResponseDto.MyMissionDto> myMissionDtoList = userMissionSlice.getContent().stream()
                .map(MissionConverter::toMyMissionDto)
                .collect(Collectors.toList());

        return SliceResponseDto.<MissionResponseDto.MyMissionDto>builder()
                .list(myMissionDtoList)
                .listSize(myMissionDtoList.size())
                .hasNext(userMissionSlice.hasNext())
                .isFirst(userMissionSlice.isFirst())
                .isLast(userMissionSlice.isLast())
                .build();
    }
}