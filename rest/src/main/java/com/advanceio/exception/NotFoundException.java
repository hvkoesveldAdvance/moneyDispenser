package com.advanceio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5848425199453836673L;

    public NotFoundException(String message) {
        super(message);
    }
}
