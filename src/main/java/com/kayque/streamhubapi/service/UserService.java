package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.CreateUserRequest;
import com.kayque.streamhubapi.dto.UserResponse;
import com.kayque.streamhubapi.entity.User;
import com.kayque.streamhubapi.mapper.UserMapper;
import com.kayque.streamhubapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        User user = mapper.toEntity(request, encodedPassword);

        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }
}