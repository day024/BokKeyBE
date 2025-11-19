package com.BokKey.BokKey.api.store;

import com.BokKey.BokKey.api.store.dto.StoreDetailResponse;
import com.BokKey.BokKey.api.store.dto.StoreSimpleResponse;
import com.BokKey.BokKey.api.store.service.StoreService;
import com.BokKey.BokKey.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/filter/district")
    public ResponseEntity<ApiResponse<List<StoreSimpleResponse>>> filterByDistrict(@RequestParam String area) {
        List<StoreSimpleResponse> result = storeService.findByDistrict(area);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/status/live")
    public ResponseEntity<ApiResponse<List<StoreSimpleResponse>>> liveStores(@RequestParam boolean now) {
        List<StoreSimpleResponse> result = storeService.findLiveStores(now);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/filter/target")
    public ResponseEntity<ApiResponse<List<StoreSimpleResponse>>> filterByTarget(@RequestParam String type) {
        List<StoreSimpleResponse> result = storeService.findByTargetType(type);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{storeId}/detail")
    public ResponseEntity<ApiResponse<StoreDetailResponse>> getDetail(@PathVariable Long storeId) {
        StoreDetailResponse detail = storeService.getDetail(storeId);
        return ResponseEntity.ok(ApiResponse.success(detail));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StoreSimpleResponse>>> searchByName(@RequestParam String name) {
        List<StoreSimpleResponse> result = storeService.searchByName(name);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
