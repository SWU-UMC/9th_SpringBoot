package com.example.server_9th.domain.mapping.myMission;

import com.example.server_9th.domain.common.BaseEntity;
import com.example.server_9th.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MyMission extends BaseEntity {
    @EmbeddedId
    private MyMissionId myMission_id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private MissionStatus missionStatus = MissionStatus.PENDING;
}
