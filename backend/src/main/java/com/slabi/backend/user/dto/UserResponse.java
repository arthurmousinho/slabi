package com.slabi.backend.user.dto;

import com.slabi.backend.user.entity.User;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
