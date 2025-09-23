package ru.mrkotyaka.csfileloader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenEntity {
    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "is_active")
    private boolean isActive = true;
}
