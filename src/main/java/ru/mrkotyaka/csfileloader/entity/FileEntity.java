package ru.mrkotyaka.csfileloader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @Column(nullable = false)
    private String filename;

    @Column(name = "original_filename")
    private String originalFilename;

    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, name = "file_data")
    private byte[] fileData;

}
