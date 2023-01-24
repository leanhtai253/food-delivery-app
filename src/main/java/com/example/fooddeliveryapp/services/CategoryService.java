package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.dto.CategoryWithIdDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.NoCategoryFoundException;

import java.util.List;

public interface CategoryService {
    List<FoodViewDTO> getFoodsWithinCategory(int categoryId) throws NoCategoryFoundException;
    List<CategoryDTO> getAllCategories() throws CategoryNotExistException;
    List<CategoryWithIdDTO> getAllCategoriesWithId() throws CategoryNotExistException;
}
