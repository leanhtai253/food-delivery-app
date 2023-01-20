package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {
    public RestaurantDTO restaurantToRestaurantDTO(RestaurantEntity entity) {

        RestaurantDTO dto = new RestaurantDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setRating(entity.getRestaurantDetail().getRating());

        return dto;
    }
}
