package ru.mrkotyaka.csfileloader.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mrkotyaka.csfileloader.dto.AuthRequest;
import ru.mrkotyaka.csfileloader.dto.AuthResponse;
import ru.mrkotyaka.csfileloader.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


//    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("auth-token") String authToken) {

        System.out.println("-----------------------------------");
        System.out.println("kilian authToken: " + authToken);
        System.out.println("-----------------------------------");

        authService.logout(authToken);
    }

//    @PostMapping("/login2")
//    public ResponseEntity<Map<String, String>> login2(@Valid @RequestBody AuthRequest request) {
//
//        String token = authService.authenticate(request.getUsername(), request.getPassword());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("auth-token", token);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/logout2")
//    public ResponseEntity<Void> logout2(@RequestHeader("auth-token") String token) {
//        authService.logout(token);
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/login")
//    public ResponseEntity<Void> loginPage() {
//        return ResponseEntity.ok().build();
//    }
}
