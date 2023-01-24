package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.SearchFoodDTO;
import com.example.fooddeliveryapp.dto.SearchRestaurantDTO;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import com.example.fooddeliveryapp.mapper.SearchFoodMapper;
import com.example.fooddeliveryapp.mapper.SearchRestaurantMapper;
import com.example.fooddeliveryapp.repositories.SearchFoodRepository;
import com.example.fooddeliveryapp.repositories.SearchRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImp implements SearchService {
    @Autowired
    SearchFoodRepository searchFoodRepository;
    @Autowired

    SearchRestaurantRepository searchRestaurantRepository;
    @Autowired
    SearchFoodMapper searchFoodMapper;
    @Autowired

    SearchRestaurantMapper searchRestaurantMapper;

    @Override
    public List<SearchFoodDTO> searchByFoodName(String name) {
        List<FoodEntity> foodEntities = searchFoodRepository.searchByFoodName(name);
        List<SearchFoodDTO> searchFoodDTOS = new ArrayList<>();
        for (FoodEntity food: foodEntities) {
            searchFoodDTOS.add(searchFoodMapper.searchToSearchFoodDTO(food));
        }
        return searchFoodDTOS;
    }
    @Override
    public List<SearchRestaurantDTO> searchByRestaurantName(String name) {
        List<RestaurantEntity> restaurantEntities = searchRestaurantRepository.searchByRestaurantName(name);
        System.out.println("test" + restaurantEntities);
        List<SearchRestaurantDTO> searchRestaurantDTOS = new ArrayList<>();
        for (RestaurantEntity restaurant: restaurantEntities) {
            searchRestaurantDTOS.add(searchRestaurantMapper.searchToSearchResDTO(restaurant));
        }
        return searchRestaurantDTOS;
    }
}
