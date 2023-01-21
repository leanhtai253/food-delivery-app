package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ItemDTO implements Serializable {
    @NotNull(message = "Product ID is required.")
    @Min(value = 1, message = "Product ID is not valid.")
    private int productId=-1;

    @NotNull(message = "Product quantity is required.")
    @Min(value = 1, message = "Product quantity must be at least 1.")
    private int quantity=-1;

    @NotNull(message = "Product price is required.")
    @Min(value = 0, message = "Product price is not valid.")
    private float price=-1;
}
