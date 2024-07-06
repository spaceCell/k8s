package com.iprody.controller;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDto {

    private final String message;

    private final LocalDateTime timestamp;

    public ErrorDto(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
