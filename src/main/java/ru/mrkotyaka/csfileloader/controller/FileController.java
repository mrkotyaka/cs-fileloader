package ru.mrkotyaka.csfileloader.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mrkotyaka.csfileloader.dto.FileNameInRequest;
import ru.mrkotyaka.csfileloader.dto.FileResponse;
import ru.mrkotyaka.csfileloader.service.AuthService;
import ru.mrkotyaka.csfileloader.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FileController {

    private final AuthService authService;
    private final FileService fileService;

    @GetMapping("/list")
    public List<FileResponse> listFiles(
            @RequestHeader("auth-token") String authToken,
            @Min(1) int limit) {
        authService.validateToken(authToken);
        return fileService.getAllFiles(limit);
    }

    @GetMapping(value = "/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] downloadFile(
            @RequestHeader("auth-token") String authToken, String filename) {

        var user =authService.validateToken(authToken);

        return fileService.downloadFile(filename, user);
    }

    @PostMapping("/file")
    public ResponseEntity<Void> uploadFile(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("file") MultipartFile file) {

        var user = authService.validateToken(authToken);
        authService.validateToken(authToken);
        fileService.uploadFile(file, user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(
            @RequestHeader("auth-token") String authToken, String filename) {

        var user = authService.validateToken(authToken);
        fileService.deleteFile(filename, user);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/file")
    public void editFileName(
            @RequestHeader("auth-token") String authToken, String filename,
            @RequestBody FileNameInRequest newFileName) {
        var user = authService.validateToken(authToken);
        fileService.editFileName(filename, newFileName.getFilename(), user);
    }
}