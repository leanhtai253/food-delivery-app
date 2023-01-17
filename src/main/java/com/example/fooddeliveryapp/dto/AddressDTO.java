package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private int number;
    private String addressType;
    private String state;
    private String city;
    private String street;
}
