package com.example.umc.domain.mission.mapping;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.member.entity.User;
import com.example.umc.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user_mission",
        indexes = {
                @Index(name = "idx_user_mission_user", columnList = "user_id"),
                @Index(name = "idx_user_mission_mission", columnList = "mission_id")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long userMissionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private MissionStatus status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
