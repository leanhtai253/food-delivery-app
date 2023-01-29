package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.SearchRestaurantDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchRestaurantMapper {
    public SearchRestaurantDTO searchToSearchResDTO(RestaurantEntity entity) {
        SearchRestaurantDTO dto = new SearchRestaurantDTO();

        dto.setNameRes(entity.getName());
        dto.setImageRes(entity.getImage());
        dto.setCateRes(entity.getRestaurantDetail().getNationality());
        dto.setRatingRes(entity.getRestaurantDetail().getRating());
        return dto;
    }
}
