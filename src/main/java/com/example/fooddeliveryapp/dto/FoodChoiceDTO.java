package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodChoiceDTO {
    private int id;
    private String name;
    private String image;
    private float price;
    private String description;
    private List foodAddOn;
}
