package com.example.umc9th.global.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "무한 스크롤(Slice) 응답 공통 DTO")
public class SliceResponseDto<T> {

    @Schema(description = "리스트 데이터")
    private List<T> list;

    @Schema(description = "현재 페이지 번호 (1-based)", example = "1")
    private Integer listSize;

    @Schema(description = "다음 페이지 존재 여부", example = "true")
    private Boolean hasNext;

    @Schema(description = "첫 페이지 여부", example = "true")
    private Boolean isFirst;

    @Schema(description = "마지막 페이지 여부", example = "false")
    private Boolean isLast;
}