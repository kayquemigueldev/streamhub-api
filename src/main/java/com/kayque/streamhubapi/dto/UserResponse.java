package com.kayque.streamhubapi.dto;

import com.kayque.streamhubapi.entity.UserRole;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        UserRole role,
        LocalDateTime createdAt
) {
}