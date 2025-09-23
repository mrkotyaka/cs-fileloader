package ru.mrkotyaka.csfileloader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int id;
}