package com.example.leeseo.domain.store.controller;

import com.example.leeseo.domain.store.dto.StoreReqDTO;
import com.example.leeseo.domain.store.dto.StoreResDTO;
import com.example.leeseo.domain.store.exception.code.StoreSuccessCode;
import com.example.leeseo.domain.store.service.StoreService;
import com.example.leeseo.global.entity.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("location/{locationId}/addStore")
    public ApiResponse<StoreResDTO.JoinDTO> saveStore(
            @PathVariable Long locationId,
            @Valid @RequestBody StoreReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.OK, storeService.saveStore(locationId, dto));
    }
}
