package com.BokKey.BokKey.api.Auth.dto;


public record SignupResponse(
        Long userId,
        String email,
        String name
) {}
