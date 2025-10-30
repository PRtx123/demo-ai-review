package com.example.demo.exception;

public class UserUniqueEmailViolationException extends RuntimeException {
    public UserUniqueEmailViolationException(String email) {
        super("Email already in use: " + email);
    }
}



