package com.BokKey.BokKey.api.Auth.controller;

import com.BokKey.BokKey.api.Auth.dto.*;
import com.BokKey.BokKey.api.Auth.service.AuthService;
import com.BokKey.BokKey.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest request) {
        SignupResponse response = authService.signup(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<ApiResponse<LoginResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request) {
        LoginResponse response = authService.kakaoLogin(request.accessToken());
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
