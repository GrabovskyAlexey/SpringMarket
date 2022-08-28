package ru.grabovsky.productfront.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.grabovsky.productfront.dto.MessageDto;
import ru.grabovsky.productfront.exceptions.BadRequestException;
import ru.grabovsky.productfront.exceptions.ResourceNotFoundException;
import ru.grabovsky.productfront.exceptions.UserAlreadyExistException;
import ru.grabovsky.productfront.exceptions.ValidationErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchBadRequestException(BadRequestException e){
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchValidationErrorException(ValidationErrorException e){
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchUserAlreadyExistsException(UserAlreadyExistException e){
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public void catchHttpMessageNotReadableException(HttpMessageNotReadableException e){
        System.out.println(e.getLocalizedMessage());
    }
}
