package com.example.EAT_demo.exception;

public class EmailAuthExpiredException extends RuntimeException {
    public EmailAuthExpiredException(String message) {
        super(message);
    }
}
