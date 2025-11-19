package com.BokKey.BokKey.api.store.service;

import com.BokKey.BokKey.api.store.StoreRepository;
import com.BokKey.BokKey.api.store.domain.Store;
import com.BokKey.BokKey.api.store.dto.StoreDetailResponse;
import com.BokKey.BokKey.api.store.dto.StoreSimpleResponse;
import com.BokKey.BokKey.global.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreSimpleResponse> findByDistrict(String area) {
        return storeRepository.findByArea(area)
                .stream()
                .map(StoreSimpleResponse::from)
                .toList();
    }

    public List<StoreSimpleResponse> findLiveStores(boolean now) {
        return storeRepository.findByIsLive(now)
                .stream()
                .map(StoreSimpleResponse::from)
                .toList();
    }

    public List<StoreSimpleResponse> findByTargetType(String type) {
        return storeRepository.findBySupportType(type)
                .stream()
                .map(StoreSimpleResponse::from)
                .toList();
    }

    public StoreDetailResponse getDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));
        return StoreDetailResponse.from(store);
    }

    public List<StoreSimpleResponse> searchByName(String name) {
        return storeRepository.findByNameContaining(name)
                .stream()
                .map(StoreSimpleResponse::from)
                .toList();
    }
}
