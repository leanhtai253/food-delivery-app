package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.FoodChoiceDTO;
import com.example.fooddeliveryapp.dto.FoodDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.FoodAddOnEntity;
import com.example.fooddeliveryapp.entities.FoodDetailEntity;
import com.example.fooddeliveryapp.entities.FoodEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodMapper {
    public FoodViewDTO foodToFoodViewDto(FoodEntity entity) {
        FoodViewDTO dto = new FoodViewDTO();
        dto.setNumber(entity.getId());
        dto.setName(entity.getName());
        dto.setArea(entity.getArea());
        dto.setImage(entity.getImage());
        dto.setPrice(entity.getPrice());

        dto.setOrders(entity.getOrderDetails() != null ? entity.getOrderDetails().size() : 0);
        dto.setCategory(entity.getCategory() != null ? entity.getCategory().getName() : "");
        dto.setRating(entity.getFoodDetail() != null ? entity.getFoodDetail().getRating() : 0);

        return dto;
    }

    public FoodDTO foodToFoodDto(FoodEntity entity) {
        FoodDTO dto = new FoodDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    public FoodEntity foodDTO2Food(FoodDTO dto) {
        FoodEntity entity = new FoodEntity();
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setArea(dto.getArea());
        return entity;
    }

    public FoodChoiceDTO foodToFoodChoiceDto(FoodEntity food){
        FoodChoiceDTO dto = new FoodChoiceDTO();
        dto.setId(food.getId());
        dto.setName(food.getName());
        dto.setImage(food.getImage());
        dto.setPrice(food.getPrice());
        dto.setDescription(food.getFoodDetail().getDescription());
        List<FoodAddOnEntity> list = new ArrayList<>(food.getFoodAddOns());
        dto.setFoodAddOn(list);
        return dto;
    }
}
