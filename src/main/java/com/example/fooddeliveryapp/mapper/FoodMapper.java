package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.FoodEntity;
import org.springframework.stereotype.Service;

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
}
