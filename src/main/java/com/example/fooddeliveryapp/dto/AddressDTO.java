package com.example.fooddeliveryapp.dto;

import com.example.fooddeliveryapp.utils.Constant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressDTO {
    private int number;
    @NotEmpty(message = "Address type must be specified as Home or Work.")
    private String addressType;
    @NotEmpty(message = "State cannot be blank.")
    private String state;
    @NotEmpty(message = "City cannot be blank.")
    private String city;
    @NotEmpty(message = "Street cannot be blank.")
    private String street;
}
