package com.BokKey.BokKey.api.store.dto;


import com.BokKey.BokKey.api.store.domain.Store;

public record StoreDetailResponse(
        Long storeId,
        String name,
        String address,
        String phone,
        String openHours,
        boolean isFavorite,
        String areaName
) {
    public static StoreDetailResponse from(Store store) {
        return new StoreDetailResponse(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getPhone(),
                store.getOpenHours(),
                false, 
                store.getArea()
        );
    }
}
