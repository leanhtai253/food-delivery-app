package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.SearchFoodDTO;
import com.example.fooddeliveryapp.dto.SearchRestaurantDTO;

import java.util.List;

public interface SearchService {
    List<SearchFoodDTO> searchByFoodName(String name);
    List<SearchRestaurantDTO> searchByRestaurantName(String name);

}

