package com.example.authservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private String message;
    private Date date;

    public MessageDto() {
        this.date = new Date();
    }

    public MessageDto(String message) {
        this.message = message;
        this.date = new Date();
    }
}
