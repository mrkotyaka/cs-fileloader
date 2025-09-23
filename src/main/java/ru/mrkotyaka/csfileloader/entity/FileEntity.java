package ru.mrkotyaka.csfileloader.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class FileEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Column(nullable = false)
    private String filename;

    @Column(name = "original_filename")
    private String originalFilename;

    private Long size;

    @Column(name = "content_type")
    private String contentType;

//    @Column(name = "storage_path")
//    private String storagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, name = "file_data")
    private byte[] fileData;

}
