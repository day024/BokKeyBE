package com.BokKey.BokKey.api.Auth.dto;

public record SignupRequest(
        String email,
        String password,
        String name,
        String birthDate,
        String phoneNumber
) {}