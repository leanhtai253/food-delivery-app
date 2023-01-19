package com.example.fooddeliveryapp.exceptions;

public class CategoryNotExistException extends Exception {
    public CategoryNotExistException() {
        super("Not exist category");
    }
}
