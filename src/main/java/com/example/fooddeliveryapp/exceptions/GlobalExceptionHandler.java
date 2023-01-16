package com.example.fooddeliveryapp.exceptions;

import com.example.fooddeliveryapp.payload.response.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle invalid arguments
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotvalid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });
        return new ResponseEntity<>(buildResponseError(HttpStatus.BAD_REQUEST,errors), HttpStatus.BAD_REQUEST);
    }

    private ResponseError buildResponseError(HttpStatus status, Object errors) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus(status.value());
        responseError.setMessage(errors);
        return responseError;
    }
}
