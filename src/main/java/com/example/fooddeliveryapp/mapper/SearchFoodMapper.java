package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.SearchFoodDTO;
import com.example.fooddeliveryapp.entities.FoodEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchFoodMapper {
    public SearchFoodDTO searchToSearchFoodDTO(FoodEntity entity) {
        SearchFoodDTO dto = new SearchFoodDTO();
        dto.setIdDish(entity.getId());
        dto.setImageDish(entity.getImage());
        dto.setNameDish(entity.getName());
        dto.setCateDish(entity.getCategory().getName());
        dto.setRatingDish(entity.getFoodDetail().getRating());
        return dto;
    }
}
