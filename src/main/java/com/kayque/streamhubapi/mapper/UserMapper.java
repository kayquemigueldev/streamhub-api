package com.kayque.streamhubapi.mapper;

import com.kayque.streamhubapi.dto.CreateUserRequest;
import com.kayque.streamhubapi.dto.UserResponse;
import com.kayque.streamhubapi.entity.User;
import com.kayque.streamhubapi.entity.UserRole;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(CreateUserRequest request, String encodedPassword) {
        return User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(encodedPassword)
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}