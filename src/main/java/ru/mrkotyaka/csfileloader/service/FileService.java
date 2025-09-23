package ru.mrkotyaka.csfileloader.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mrkotyaka.csfileloader.dto.FileResponse;
import ru.mrkotyaka.csfileloader.entity.FileEntity;
import ru.mrkotyaka.csfileloader.entity.UserEntity;
import ru.mrkotyaka.csfileloader.exception.FileUploadException;
import ru.mrkotyaka.csfileloader.exception.RenameFileException;
import ru.mrkotyaka.csfileloader.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    public List<FileResponse> getAllFiles(int limit) {
        final List<FileEntity> files = fileRepository.getFiles(limit);
        return files.stream()
                .map(file -> new FileResponse(file.getFilename(), file.getFileData().length))
                .collect(Collectors.toList());
    }


    @Transactional
    public void uploadFile(MultipartFile file, UserEntity user) {
        try {
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String uniqueFilename = generateUniqueFilename(fileExtension);

            fileRepository.save(new FileEntity(uniqueFilename, originalFilename, file.getSize(), file.getContentType(), user, file.getBytes()));
        } catch (IOException e) {
            throw new FileUploadException("Error upload file!");
        }
    }

    public byte[] downloadFile(String filename, UserEntity user) {
        final FileEntity file = fileRepository.findByFilenameAndUser(filename, user);
        return file.getFileData();
    }

    @Transactional
    public void deleteFile(String filename, UserEntity user) {
        FileEntity fileEntity = fileRepository.findByFilenameAndUser(filename, user);

        if (isNull(fileEntity)) {
            throw new RenameFileException("File is not exist!");
        }

        fileRepository.delete(fileEntity);
    }


    public synchronized void editFileName(String fileName, String newFilename, UserEntity user) {
        final FileEntity fileEntity = fileRepository.findByFilenameAndUser(fileName, user);
        if (isNull(fileEntity)) {
            throw new RenameFileException("File is not exist!");
        }
        final FileEntity newFileEntity = new FileEntity(
                newFilename,
                fileEntity.getOriginalFilename(),
                fileEntity.getSize(),
                fileEntity.getContentType(),
                user,
                fileEntity.getFileData());
        fileRepository.delete(fileEntity);
        fileRepository.save(newFileEntity);
    }

//    public List<FileResponse> getUserFiles(User user) {
//        return fileRepository.findByUser(user).stream()
//                .map(this::convertToResponse)
//                .collect(Collectors.toList());
//    }

//    public FileEntity getFile(String filename, User user) {
//        return fileRepository.findByFilenameAndUser(filename, user)
//                .orElseThrow(() -> new RuntimeException("File not found"));
//    }

//    public Resource loadFileResource(FileEntity fileEntity) {
//        try {
//            return storageService.load(fileEntity.getFilename());
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to load file", e);
//        }
//    }

//    private FileResponse convertToResponse(FileEntity fileEntity) {
//        FileResponse response = new FileResponse();
//        response.setId(fileEntity.getId());
//        response.setFilename(fileEntity.getFilename());
//        response.setOriginalFilename(fileEntity.getOriginalFilename());
//        response.setSize(fileEntity.getSize());
//        response.setContentType(fileEntity.getContentType());
//        response.setUploadDate(fileEntity.getUploadDate());
//        return response;
//    }

    private String generateUniqueFilename(String extension) {
        return UUID.randomUUID().toString() + (extension != null ? "." + extension : "");
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return null;
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}