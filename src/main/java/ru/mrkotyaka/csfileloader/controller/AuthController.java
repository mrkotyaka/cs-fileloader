package ru.mrkotyaka.csfileloader.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mrkotyaka.csfileloader.dto.AuthRequest;
import ru.mrkotyaka.csfileloader.dto.AuthResponse;
import ru.mrkotyaka.csfileloader.service.AuthService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("auth-token") String authToken) {
        authService.logout(authToken);
    }
}
