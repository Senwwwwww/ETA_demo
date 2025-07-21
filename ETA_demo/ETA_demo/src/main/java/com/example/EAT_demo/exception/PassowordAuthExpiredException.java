package com.example.EAT_demo.exception;

public class PassowordAuthExpiredException extends RuntimeException{
    public PassowordAuthExpiredException(String message) {
        super(message);
    }
}
