package com.example.PowerToolStore.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found in db with id: "+userId);
    }
}
