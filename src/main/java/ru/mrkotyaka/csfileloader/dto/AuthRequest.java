package ru.mrkotyaka.csfileloader.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
