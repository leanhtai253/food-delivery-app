package com.example.fooddeliveryapp.utils;

import com.example.fooddeliveryapp.dto.FoodViewDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FoodSorting {

    public List<FoodViewDTO> sortFoodViewDtosByRating(List<FoodViewDTO> foods) {
        return foods.stream()
                .sorted(Comparator.comparing(FoodViewDTO::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List<FoodViewDTO> sortFoodViewDtosByPopularity(List<FoodViewDTO> foods) {
        return foods.stream()
                .sorted(Comparator.comparing(FoodViewDTO::getOrders).reversed())
                .collect(Collectors.toList());
    }

    public List<FoodViewDTO> sortFoodViewDtosByPrice(List<FoodViewDTO> foods, boolean priceAsc) {
        if (priceAsc) {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getPrice)).collect(Collectors.toList());
        } else {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getPrice).reversed()).collect(Collectors.toList());
        }
    }

    public List<FoodViewDTO> sortFoodViewDtosByRatingThenPrice(List<FoodViewDTO> foods, boolean priceAsc) {
        if (priceAsc) {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getRating).reversed()
                            .thenComparing(FoodViewDTO::getPrice))
                    .collect(Collectors.toList());
        } else {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getRating)
                            .thenComparing(FoodViewDTO::getPrice).reversed())
                    .collect(Collectors.toList());
        }
    }

    public List<FoodViewDTO> sortFoodViewDtosByPopularityThenPrice(List<FoodViewDTO> foods, boolean priceAsc) {
        if (priceAsc) {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getOrders).reversed()
                            .thenComparing(FoodViewDTO::getPrice))
                    .collect(Collectors.toList());
        } else {
            return foods.stream()
                    .sorted(Comparator.comparing(FoodViewDTO::getOrders)
                            .thenComparing(FoodViewDTO::getPrice).reversed())
                    .collect(Collectors.toList());
        }
    }
}
