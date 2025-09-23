package ru.mrkotyaka.csfileloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mrkotyaka.csfileloader.entity.FileEntity;
import ru.mrkotyaka.csfileloader.entity.UserEntity;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findByUser(UserEntity user);

    @Query(value = "SELECT * FROM files LIMIT :limit", nativeQuery = true)
    List<FileEntity> getFiles(int limit);

    FileEntity findByFilenameAndUser(String filename, UserEntity user);
}
