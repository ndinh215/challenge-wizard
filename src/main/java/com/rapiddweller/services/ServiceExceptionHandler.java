package com.rapiddweller.services;

import com.rapiddweller.models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseError> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}