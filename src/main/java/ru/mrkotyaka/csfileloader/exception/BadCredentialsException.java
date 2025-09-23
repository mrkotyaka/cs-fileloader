package ru.mrkotyaka.csfileloader.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
