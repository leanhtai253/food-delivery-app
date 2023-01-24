package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.dto.CategoryWithIdDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.NoCategoryFoundException;
import com.example.fooddeliveryapp.mapper.CategoryMapper;
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

    @Autowired
    CategoryMapper categoryMapper;

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

    @Override
    public List<CategoryDTO> getAllCategories() throws CategoryNotExistException {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if(categoryEntities != null) {
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            categoryEntities.forEach((category) -> {
                categoryDTOS.add(categoryMapper.categoryToCategoryDTO(category));
            });
            return categoryDTOS;
        } else {
            throw new CategoryNotExistException();
        }
    }

    @Override
    public List<CategoryWithIdDTO> getAllCategoriesWithId() throws CategoryNotExistException {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if(categoryEntities != null) {
            List<CategoryWithIdDTO> dtos = new ArrayList<>();
            categoryEntities.forEach((category) -> {
                dtos.add(categoryMapper.categoryToCategoryWithIdDTO(category));
            });
            return dtos;
        } else {
            throw new CategoryNotExistException();
        }
    }
}
