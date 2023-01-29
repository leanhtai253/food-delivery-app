package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.dto.CategoryFromRestaurantDetailDTO;
import com.example.fooddeliveryapp.dto.CategoryWithIdDTO;

import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDTO categoryToCategoryDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setCount(entity.getFoods().size());
        return dto;
    }

    public CategoryFromRestaurantDetailDTO toCategoryFromRestaurantDetailDTO(CategoryEntity category){
        CategoryFromRestaurantDetailDTO cateFromResDTO = new CategoryFromRestaurantDetailDTO();
        cateFromResDTO.setName(category.getName());
        return cateFromResDTO;
    }

    public CategoryWithIdDTO categoryToCategoryWithIdDTO(CategoryEntity entity) {
        CategoryWithIdDTO dto = new CategoryWithIdDTO();
        dto.setName(entity.getName());
        dto.setNumber(entity.getId());
        return dto;
    }

    public IdCateNameDTO categoryToIdCateNameDTO(CategoryEntity entity){
        IdCateNameDTO dto = new IdCateNameDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
