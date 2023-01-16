package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodViewDTO {
    private int number;
    private String name;
    private String category;
    private float price;
    private float rating;
    private String image;
    private String area;
    private int orders;
}
