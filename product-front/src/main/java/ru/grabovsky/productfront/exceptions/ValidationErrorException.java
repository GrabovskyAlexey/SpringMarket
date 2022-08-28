package ru.grabovsky.productfront.exceptions;

import java.util.List;

public class ValidationErrorException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ValidationErrorException(List<String> messages) {
        this.message = String.join("\n", messages);
    }
}
