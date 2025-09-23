package ru.mrkotyaka.csfileloader.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String authToken;

    @JsonGetter("auth-token")
    public String getAuthToken() {
        return authToken;
    }
}
