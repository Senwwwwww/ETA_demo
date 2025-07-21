package com.example.EAT_demo.exception;

public class TokenAuthExpiredException extends RuntimeException{
    public TokenAuthExpiredException(String message) {
        super(message);
    }
}

