package com.example.server_9th.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionPreviewDto {
        private Long missionId;
        private String storeName;
        private String title;
        private String description;
        private LocalDate assignedDate;
        private String status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionPreviewListDto {
        private java.util.List<MissionPreviewDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
