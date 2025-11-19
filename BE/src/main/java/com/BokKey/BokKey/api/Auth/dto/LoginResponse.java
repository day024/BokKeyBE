package com.BokKey.BokKey.api.Auth.dto;

public record LoginResponse(
        Long userId,
        String email,
        String name,
        String Token
) {}