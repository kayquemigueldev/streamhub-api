package com.kayque.streamhubapi.service;

import com.kayque.streamhubapi.dto.LoginRequest;
import com.kayque.streamhubapi.dto.LoginResponse;
import com.kayque.streamhubapi.entity.User;
import com.kayque.streamhubapi.repository.UserRepository;
import com.kayque.streamhubapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.password(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}