package com.example.leeseo.domain.mission.enums;

import com.example.leeseo.domain.mission.exception.MissionException;
import com.example.leeseo.domain.mission.exception.code.MissionErrorCode;

public enum MissionStatus {
    IN_PROGRESS, SUCCESS, FAIL;

    public static MissionStatus from(String raw) {
        try {
            return MissionStatus.valueOf(raw.toUpperCase());
        } catch (Exception e) {
            throw new MissionException(MissionErrorCode.INVALID_STATUS);
        }
    }
}
