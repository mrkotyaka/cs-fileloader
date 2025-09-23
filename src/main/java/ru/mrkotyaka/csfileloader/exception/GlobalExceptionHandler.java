package ru.mrkotyaka.csfileloader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mrkotyaka.csfileloader.dto.ErrorResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final AtomicInteger errorId = new AtomicInteger(1);

    @ExceptionHandler(value = {BindException.class, BadCredentialsException.class, IOException.class})
    public ResponseEntity<ErrorResponse> handleBindException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handlerException(FileUploadException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteFileException.class)
    public ResponseEntity<ErrorResponse> handlerException(DeleteFileException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RenameFileException.class)
    public ResponseEntity<ErrorResponse> handlerException(RenameFileException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), errorId.getAndIncrement()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
