package com.example.fooddeliveryapp.exceptions;

public class RestaurantNotExistException extends Exception {
    public RestaurantNotExistException() {
        super("Not exist restaurants");
    }
}
