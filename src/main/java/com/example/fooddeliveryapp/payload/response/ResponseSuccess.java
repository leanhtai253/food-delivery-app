package com.example.fooddeliveryapp.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSuccess {
    private int status;
    private boolean success = true;
    private Object data;
}
