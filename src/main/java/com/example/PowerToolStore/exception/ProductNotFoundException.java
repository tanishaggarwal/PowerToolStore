package com.example.PowerToolStore.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long productId)
    {
        super("Product not found in db with id: "+ productId);
    }
}
