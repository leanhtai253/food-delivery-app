package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.FoodChoiceDTO;
import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDto;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantMapper {
    @Autowired
    FoodMapper foodMapper;

    public RestaurantDTO restaurantToRestaurantDTO(RestaurantEntity entity) {

        RestaurantDTO dto = new RestaurantDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setNationality(entity.getRestaurantDetail() != null ? entity.getRestaurantDetail().getNationality() : "");
        dto.setRating(entity.getRestaurantDetail() != null ? entity.getRestaurantDetail().getRating() : 1);
        dto.setId(entity.getId());
        return dto;
    }

    public RestaurantDetailDto restaurantToRestaurantDetailDto(RestaurantEntity entity) {
        RestaurantDetailDto dto = new RestaurantDetailDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        List<FoodChoiceDTO> foods = new ArrayList<>();
        for (FoodEntity food : entity.getFoods()) {
            foods.add(foodMapper.foodToFoodChoiceDto(food));
        }
        dto.setFoods(foods);
        dto.setDescription(entity.getRestaurantDetail().getDescription());
        dto.setNationality(entity.getRestaurantDetail().getNationality());
        dto.setJoinDate(entity.getRestaurantDetail().getJoinDate());
        dto.setRating(entity.getRestaurantDetail().getRating());
        return dto;
    }
}