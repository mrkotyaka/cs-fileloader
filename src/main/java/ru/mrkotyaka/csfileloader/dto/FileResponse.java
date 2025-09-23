package ru.mrkotyaka.csfileloader.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String filename;
    private int size;
}
