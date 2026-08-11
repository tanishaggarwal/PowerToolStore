package com.example.PowerToolStore.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId) {
        super("Insufficient stock for product with id: "+productId);
    }

    public InsufficientStockException(Long productId, String message) {
        super("Insufficient stock for product with id: "+productId+" "+message);
    }
}
