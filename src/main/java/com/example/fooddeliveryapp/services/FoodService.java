package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.FoodEntity;

import java.util.List;

public interface FoodService {
    public List<FoodViewDTO> sortFoods(List<FoodViewDTO> foods, String sortBy, String price);
    public List<FoodViewDTO> getAllFoods();
}
