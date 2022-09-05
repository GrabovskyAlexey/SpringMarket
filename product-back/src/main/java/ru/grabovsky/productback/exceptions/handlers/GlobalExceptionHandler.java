package ru.grabovsky.productback.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.grabovsky.productback.dto.MessageDto;
import ru.grabovsky.productback.exceptions.BadRequestException;
import ru.grabovsky.productback.exceptions.ResourceNotFoundException;
import ru.grabovsky.productback.exceptions.UserAlreadyExistException;
import ru.grabovsky.productback.exceptions.ValidationErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchBadRequestException(BadRequestException e){

        return new ResponseEntity<>(new MessageDto().message(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new MessageDto().message(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchValidationErrorException(ValidationErrorException e){
        return new ResponseEntity<>(new MessageDto().message(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchUserAlreadyExistsException(UserAlreadyExistException e){
        return new ResponseEntity<>(new MessageDto().message(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public void catchHttpMessageNotReadableException(HttpMessageNotReadableException e){
        System.out.println(e.getLocalizedMessage());
    }
}
