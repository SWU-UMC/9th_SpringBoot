package com.umc.umc9th.domain.mission.controller;

import com.umc.umc9th.domain.mission.dto.MissionReqDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO;
import com.umc.umc9th.domain.mission.dto.MissionResDTO.ChallengeDTO;
import com.umc.umc9th.domain.mission.exception.MissionSuccessCode;
import com.umc.umc9th.domain.mission.service.MissionService;
import com.umc.umc9th.global.apiPayload.ApiResponse;
import com.umc.umc9th.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

  private final MissionService missionService;

  @PostMapping("/challenge")
  public ApiResponse<ChallengeDTO> challengeMission(
      @RequestBody MissionReqDTO.ChallengeDTO dto
  ) {
    Integer userId = 1;

    return ApiResponse.onSuccess(
        MissionSuccessCode.CHALLENGED,
        missionService.challengeMission(userId, dto)
    );
  }

  @Operation(
      summary = "특정 가게의 미션 목록 조회 API",
      description = "특정 가게의 미션을 페이징 처리하여 조회합니다. 마감일 기준 오름차순으로 정렬됩니다."
  )
  @ApiResponses({
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200",
          description = "미션 목록 조회 성공"
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "400",
          description = "잘못된 요청 (잘못된 페이지 번호)"
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "404",
          description = "가게를 찾을 수 없음"
      )
  })
  @GetMapping("/stores/{storeId}")
  public ApiResponse<MissionResDTO.MissionListDTO> getStoreMissions(
      @PathVariable Integer storeId,
      @RequestParam(defaultValue = "1") @CheckPage Integer page
  ) {
    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("deadline").ascending());

    MissionResDTO.MissionListDTO result = missionService.getStoreMissions(storeId, pageable);

    return ApiResponse.onSuccess(MissionSuccessCode.FOUND, result);
  }
}
