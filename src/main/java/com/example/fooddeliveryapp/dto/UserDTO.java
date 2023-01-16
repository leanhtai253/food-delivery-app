package com.example.fooddeliveryapp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String verifyCode;
    private String verifyCodeExpired;
    boolean isActive;
}
