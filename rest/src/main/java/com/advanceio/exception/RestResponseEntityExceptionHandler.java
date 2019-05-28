package com.advanceio.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private class ErrorMessageDTO {
        private String errorMessage;
        private HttpStatus code;

        public ErrorMessageDTO(HttpStatus code, String errorMessage) {
            this.errorMessage = errorMessage;
            this.code = code;
        }

        private String getErrorMessage() {
            return errorMessage;
        }

        private void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        private HttpStatus getCode() {
            return code;
        }

        private void setCode(HttpStatus code) {
            this.code = code;
        }

    }

    @ResponseBody
    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        ErrorMessageDTO error = new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleExceptionInternal(ex, error.getErrorMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ResponseBody
    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        ErrorMessageDTO error = new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleExceptionInternal(ex, error.getErrorMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}