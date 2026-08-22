package com.example.PowerToolStore.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long cartItemId) {
        super("CartItem not found for given cartItemId: " + cartItemId);
    }
}
