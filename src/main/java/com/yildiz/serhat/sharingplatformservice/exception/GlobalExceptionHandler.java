package com.yildiz.serhat.sharingplatformservice.exception;

import com.yildiz.serhat.sharingplatformservice.domain.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException exception) {
        log.error("Exception occurred: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createApiResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleServiceExceptions(RuntimeException exception) {
        log.error(exception.getClass().getName(), exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createApiResponse(HttpStatus.BAD_REQUEST.value(), "Can't save the same item"));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException exception) {
        log.error(exception.getClass().getName(), exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.createApiResponse(HttpStatus.NOT_FOUND.value(), "Not Found"));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiResponse> handleMethodNotAllowedException(MethodNotAllowedException exception) {
        log.error(exception.getClass().getName(), exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createApiResponse(HttpStatus.BAD_REQUEST.value(), "Method Not Allowed"));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiResponse> handleEmptyResult(EmptyResultDataAccessException exception) {
        log.error(exception.getClass().getName(), exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createApiResponse(HttpStatus.BAD_REQUEST.value(), "Cannot perform the operation"));
    }

    @ExceptionHandler(FileNotValidException.class)
    public ResponseEntity<?> handleServiceExceptions(FileNotValidException exception) {
        log.error("Exception occurred: {}, httpStatus: {}", exception.getMessage(), exception.getHttpStatus());
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getMessage());
    }

}
