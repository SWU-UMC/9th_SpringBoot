package com.example.server_9th.domain.mapping.myMission;

import com.example.server_9th.domain.Member;
import com.example.server_9th.domain.Mission;
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

    @Setter
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private MissionStatus missionStatus = MissionStatus.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mission_id")
    @JoinColumn(name = "mission_id")
    private Mission mission;

}
