package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.exceptions.NoCategoryFoundException;
import com.example.fooddeliveryapp.mapper.FoodMapper;
import com.example.fooddeliveryapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<FoodViewDTO> getFoodsWithinCategory(int categoryId) throws NoCategoryFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId);
        if (categoryEntity != null) {
            List<FoodViewDTO> foods = new ArrayList<>();
            categoryEntity.getFoods().forEach((food) -> {
                foods.add(foodMapper.foodToFoodViewDto(food));
            });
            return foods;
        } else {
            throw new NoCategoryFoundException(categoryId);
        }
    }
}
