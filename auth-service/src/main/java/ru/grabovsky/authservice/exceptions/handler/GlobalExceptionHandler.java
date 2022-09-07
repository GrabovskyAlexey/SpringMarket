package ru.grabovsky.authservice.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.grabovsky.authservice.exceptions.UserAlreadyExistException;
import ru.grabovsky.authservice.models.dto.MessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchUserAlreadyExistsException(UserAlreadyExistException e){
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public void catchHttpMessageNotReadableException(HttpMessageNotReadableException e){
        System.out.println(e.getLocalizedMessage());
    }
}
