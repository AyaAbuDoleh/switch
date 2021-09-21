package com.example.demo.controller;

import com.example.demo.exceptions.SwitchException;
import com.example.demo.resourcse.ApiError;
import com.example.demo.resourcse.ResponseEntityBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SwitchException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            SwitchException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getMessage() ,
                details);

        return ResponseEntityBuilder.build(err);
    }

}