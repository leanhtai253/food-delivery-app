package com.example.fooddeliveryapp.exceptions;

public class NoAddressFoundException extends Exception{
    public NoAddressFoundException(String msg) {
        super(msg);
    }
}
