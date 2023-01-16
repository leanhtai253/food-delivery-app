package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    public List<FoodViewDTO> getFoodsWithinCategory(int categoryId);
}
