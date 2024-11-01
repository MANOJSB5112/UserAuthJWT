package com.example.userauthjwt.GlobalExceptionHandler;

import com.example.userauthjwt.ExceptionPackage.UserAlreadyExistException;
import com.example.userauthjwt.dtos.ExceptionDto;
import com.example.userauthjwt.dtos.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setResponseStatus(ResponseStatus.FAILURE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGlobalException(Exception ex) {
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setResponseStatus(ResponseStatus.FAILURE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
    }
}
