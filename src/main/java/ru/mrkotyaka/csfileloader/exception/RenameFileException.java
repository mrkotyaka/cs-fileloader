package ru.mrkotyaka.csfileloader.exception;

public class RenameFileException extends RuntimeException {
    public RenameFileException() {
    }
    public RenameFileException(String message) {
        super(message);
    }
}
