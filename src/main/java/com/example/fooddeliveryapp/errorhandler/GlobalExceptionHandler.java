package com.example.fooddeliveryapp.errorhandler;

import com.example.fooddeliveryapp.exceptions.NoCategoryFoundException;
import com.example.fooddeliveryapp.payload.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    // Handle missing request param => return NOT FOUND
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handledMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String message = "Not Found";
        return new ResponseEntity<>(buildResponseError(HttpStatus.NOT_FOUND,message), HttpStatus.NOT_FOUND);

    }
    // Handle Not Found Category with id
    @ExceptionHandler(NoCategoryFoundException.class)
    public ResponseEntity<?> handNoCategoryFoundException(NoCategoryFoundException ex) {
        return new ResponseEntity<>(buildResponseError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ResponseError buildResponseError(HttpStatus status, Object errors) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus(status.value());
        responseError.setMessage(errors);
        return responseError;
    }
}
