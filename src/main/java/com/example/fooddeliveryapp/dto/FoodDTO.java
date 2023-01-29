package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    private int id;
    private String name;
    private String image;
    private float price;
    private String area;
}
