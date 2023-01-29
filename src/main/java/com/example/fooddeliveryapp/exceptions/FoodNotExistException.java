package com.example.fooddeliveryapp.exceptions;

public class FoodNotExistException extends Exception {
    public FoodNotExistException(){
        super("Not exist Food!");
    }
}
