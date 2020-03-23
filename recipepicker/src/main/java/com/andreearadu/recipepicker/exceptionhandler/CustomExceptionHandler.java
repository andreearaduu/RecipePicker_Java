package com.andreearadu.recipepicker.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.andreearadu.recipepicker.exceptions.RecipeNotFoundException;
import com.andreearadu.recipepicker.exceptions.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

   
    @ExceptionHandler({UserNotFoundException.class,RecipeNotFoundException.class})
    public ResponseEntity<ErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setTime(LocalDateTime.now());
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    
}
