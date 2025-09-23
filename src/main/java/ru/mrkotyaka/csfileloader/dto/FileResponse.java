package ru.mrkotyaka.csfileloader.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String filename;
    private int size;
}
