package com.yildiz.serhat.sharingplatformservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FileNotValidException extends RuntimeException {

    private final HttpStatus httpStatus;

    public FileNotValidException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
