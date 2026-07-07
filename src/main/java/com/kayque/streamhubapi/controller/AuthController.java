package com.kayque.streamhubapi.controller;

import com.kayque.streamhubapi.dto.LoginRequest;
import com.kayque.streamhubapi.dto.LoginResponse;
import com.kayque.streamhubapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }
}