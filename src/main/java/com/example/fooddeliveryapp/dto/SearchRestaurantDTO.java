package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRestaurantDTO {
    private String imageRes;
    private String nameRes;
    private String cateRes;
    private float ratingRes;

    private int idDish;
    private String nameDish;
    private String imageDish;
    private String cateDish;
    private float ratingDish;
}
