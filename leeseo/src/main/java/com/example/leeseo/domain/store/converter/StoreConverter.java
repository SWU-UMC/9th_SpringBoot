package com.example.leeseo.domain.store.converter;

import com.example.leeseo.domain.store.dto.StoreReqDTO;
import com.example.leeseo.domain.store.dto.StoreResDTO;
import com.example.leeseo.domain.store.entity.Location;
import com.example.leeseo.domain.store.entity.Store;

public class StoreConverter {

    public static StoreResDTO.JoinDTO toJoinDTO(
            Store store
    ){
        return StoreResDTO.JoinDTO
                .builder()
                .store_id(store.getId())
                .createdAt(store.getCreated_at())
                .build();
    }

    public static Store toStore(
        StoreReqDTO.JoinDTO dto,
        Location location
    ){
        return Store
                .builder()
                .name(dto.name())
                .manager_number(dto.manager_number())
                .detail_address(dto.detail_address())
                .location(location)
                .build();
    }
}
