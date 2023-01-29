package com.example.fooddeliveryapp.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateUserInfoRequest {
    @NotBlank(message = "Username must not be blank")
    private String fullName;
    @NotBlank(message = "Phonenumber must not be blank")
    private String phoneNumber;
}
