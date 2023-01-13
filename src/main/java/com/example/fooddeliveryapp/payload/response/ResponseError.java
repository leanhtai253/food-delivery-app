package com.example.fooddeliveryapp.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    private int status;
    private boolean success = false;
    private Object message;
}
