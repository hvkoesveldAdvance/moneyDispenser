package com.advanceio.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessageDTO {
    private String errorMessage;
    private HttpStatus code;

    public ErrorMessageDTO() {
        super();
    }

    public ErrorMessageDTO(HttpStatus code, String errorMessage) {
        super();
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

}