package com.BokKey.BokKey.api.Auth.dto;

public record KakaoLoginResponse(
        Long userId,
        String email,
        String name,
        String token  //JWT토큰
) {}