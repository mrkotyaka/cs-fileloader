package ru.mrkotyaka.csfileloader.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mrkotyaka.csfileloader.dto.AuthRequest;
import ru.mrkotyaka.csfileloader.dto.AuthResponse;
import ru.mrkotyaka.csfileloader.entity.TokenEntity;
import ru.mrkotyaka.csfileloader.entity.UserEntity;
import ru.mrkotyaka.csfileloader.exception.BadCredentialsException;
import ru.mrkotyaka.csfileloader.repository.TokenRepository;
import ru.mrkotyaka.csfileloader.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${app.token.expiration-hours:24}")
//    private int tokenExpirationHours;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        final String login = authRequest.getUsername();
        final UserEntity user = userRepository.findByUsername(login).orElseThrow(() ->
                new BadCredentialsException("User with login " + login + " not found"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password for user " + login);
        }

        final String authToken = generateToken();
        tokenRepository.save(new TokenEntity(authToken, user, true));
        return new AuthResponse(authToken);
    }

    @Transactional
    public void logout(String token) {
        TokenEntity tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        tokenEntity.setActive(false);
        tokenRepository.save(tokenEntity);
    }

    public UserEntity validateToken(String token) {
        TokenEntity tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (!tokenEntity.isActive()) {
            throw new RuntimeException("Token is not active");
        }

        return tokenEntity.getUser();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
