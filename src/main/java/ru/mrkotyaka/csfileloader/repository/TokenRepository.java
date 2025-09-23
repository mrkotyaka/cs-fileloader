package ru.mrkotyaka.csfileloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrkotyaka.csfileloader.entity.TokenEntity;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByToken(String token);
}

