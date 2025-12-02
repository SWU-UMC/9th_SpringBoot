package com.example.leeseo.domain.store.service;

import com.example.leeseo.domain.store.converter.StoreConverter;
import com.example.leeseo.domain.store.dto.StoreReqDTO;
import com.example.leeseo.domain.store.dto.StoreResDTO;
import com.example.leeseo.domain.store.entity.Location;
import com.example.leeseo.domain.store.entity.Store;
import com.example.leeseo.domain.store.exception.StoreException;
import com.example.leeseo.domain.store.exception.code.LocationErrorCode;
import com.example.leeseo.domain.store.repository.LocationRepository;
import com.example.leeseo.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public StoreResDTO.JoinDTO saveStore(
            Long locationId,
            StoreReqDTO.JoinDTO dto
    ){
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new StoreException(LocationErrorCode.NOT_FOUND));

        Store store = StoreConverter.toStore(dto,location);
        storeRepository.save(store);

        return StoreConverter.toJoinDTO(store);
    }
}