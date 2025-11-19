package com.BokKey.BokKey.api.store.dto;

import com.BokKey.BokKey.api.store.domain.Store;

public record StoreSimpleResponse(
        Long storeId,
        String name,
        String area,
        String address,
        boolean isLive
) {
    public static StoreSimpleResponse from(Store store) {
        return new StoreSimpleResponse(
                store.getId(),
                store.getName(),
                store.getArea(),
                store.getAddress(),
                store.isLive()
        );
    }
}