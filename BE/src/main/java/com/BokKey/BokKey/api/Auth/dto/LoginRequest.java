package com.BokKey.BokKey.api.Auth.dto;

public record LoginRequest(
        String email,
        String password
) {}
