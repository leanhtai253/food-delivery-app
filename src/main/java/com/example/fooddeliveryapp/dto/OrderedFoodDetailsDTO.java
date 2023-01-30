package com.example.fooddeliveryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedFoodDetailsDTO {
    private int quantity;
    private String name;
    private float unitPrice;

}