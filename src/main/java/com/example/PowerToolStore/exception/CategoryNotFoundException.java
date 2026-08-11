package com.example.PowerToolStore.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException( Long categoryId)
    {
        super("Category not found in db with id: "+categoryId);
    }
}
