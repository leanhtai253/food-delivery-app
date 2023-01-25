package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFoodDTO {
    private String imageRes;
    private String nameRes;
    private float ratingRes;

    private int idDish;
    private String nameDish;
    private String imageDish;
}
