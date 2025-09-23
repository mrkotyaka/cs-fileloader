package ru.mrkotyaka.csfileloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrkotyaka.csfileloader.entity.TokenEntity;
import ru.mrkotyaka.csfileloader.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByToken(String token);

//    List<TokenEntity> findByUser(UserEntity user);

//    @Modifying
//    @Query("DELETE FROM TokenEntity t WHERE t.expiresAt < :dateTime")
//    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}

