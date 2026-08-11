package com.example.PowerToolStore.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String message, Throwable e) {
        super(message, e);
    }
}
