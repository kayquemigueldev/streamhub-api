package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.CreateUserRequest;
import com.kayque.streamhubapi.dto.UserResponse;
import com.kayque.streamhubapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {
        return service.create(request);
    }
}