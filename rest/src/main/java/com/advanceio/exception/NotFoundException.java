package com.advanceio.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5848425199453836673L;

    public NotFoundException(String message) {
        super(message);
    }
}
