package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDetailDto {
    private int id;
    private String name;
    private String image;
    private List<FoodChoiceDTO> foods;
    private String nationality;
    private String description;
    private String joinDate;
    private float rating;
}