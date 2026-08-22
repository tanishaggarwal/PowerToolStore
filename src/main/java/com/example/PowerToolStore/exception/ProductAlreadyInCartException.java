package com.example.PowerToolStore.exception;

public class ProductAlreadyInCartException extends RuntimeException {
    public ProductAlreadyInCartException(Long productId) {
        super("Product already exists in cart with productId: "+ productId);
    }
}
