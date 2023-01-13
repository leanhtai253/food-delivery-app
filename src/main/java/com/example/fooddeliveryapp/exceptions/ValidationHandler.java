package com.example.fooddeliveryapp.exceptions;

import com.example.fooddeliveryapp.payload.response.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
        List<String> errors = new ArrayList<>();
        ResponseError responseError = new ResponseError();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
            errors.add(error.getDefaultMessage());
//            errors.put(fieldName,message);
        });
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage(errors);
        return new ResponseEntity<Object>(responseError, HttpStatus.BAD_REQUEST);
    }

}
