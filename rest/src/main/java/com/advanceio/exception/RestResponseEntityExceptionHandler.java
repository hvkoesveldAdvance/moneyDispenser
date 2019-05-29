package com.advanceio.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Item was not found")
    @ExceptionHandler(value = { NotFoundException.class })
    protected ErrorMessageDTO handleNotFound(RuntimeException ex, WebRequest request) {
        return new ErrorMessageDTO(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<ErrorMessageDTO> handleBadRequest(RuntimeException ex, WebRequest request) {
        LOGGER.error("Handle Bad Request Exception: ${}", ex.getMessage());
        return error(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<ErrorMessageDTO> error(HttpStatus status, Exception exception) {
        return ResponseEntity.status(status).body(new ErrorMessageDTO(status, exception.getMessage()));
    }
}