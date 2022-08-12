package ru.grabovsky.market.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.grabovsky.market.dto.MessageDto;
import ru.grabovsky.market.exceptions.BadRequestException;
import ru.grabovsky.market.exceptions.ResourceNotFoundException;
import ru.grabovsky.market.exceptions.UserAlreadyExistException;
import ru.grabovsky.market.exceptions.ValidationErrorException;

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
}
