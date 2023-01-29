package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.dto.CategoryWithIdDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.NoCategoryFoundException;
import com.example.fooddeliveryapp.mapper.CategoryMapper;
import com.example.fooddeliveryapp.mapper.FoodMapper;
import com.example.fooddeliveryapp.repositories.CategoryRepository;
import com.example.fooddeliveryapp.repositories.RestaurantRepository;
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

    @Autowired
    RestaurantRepository restaurantRepository;

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

    @Override
    public List<CategoryDTO> getTop6Categoties() throws CategoryNotExistException {
        List<CategoryEntity> categoryEntities = categoryRepository.getTop6Categories();
        if(categoryEntities != null) {
            List<CategoryDTO> dtos = new ArrayList<>();
            categoryEntities.forEach((category) -> {
                dtos.add(categoryMapper.categoryToCategoryDTO(category));
            });
            return dtos;
        } else {
            throw new CategoryNotExistException();
        }
    }


    @Override
    public List<IdCateNameDTO> getAllCategoryByRestaurantId(int idRes) throws CategoryNotExistException {
        List<Object[]> list = categoryRepository.findAllByResId(idRes);
        List<IdCateNameDTO> dtos = new ArrayList<>();
        if (list != null){
            for (Object[] objects : list){
                IdCateNameDTO dto = new IdCateNameDTO();
                dto.setId((Integer)objects[0]);
                dto.setName((String)objects[1]);
                dtos.add(dto);
            }
            return dtos;
        }else {
            throw new CategoryNotExistException();
        }
    }


}
