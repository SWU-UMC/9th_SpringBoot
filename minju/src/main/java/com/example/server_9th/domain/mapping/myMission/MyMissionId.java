package com.example.server_9th.domain.mapping.myMission;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MyMissionId implements Serializable {
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "mission_id")
    private Long mission_id;
}
