package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.FoodNotExistException;
import com.example.fooddeliveryapp.exceptions.RestaurantNotExistException;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> getTop6Restaurants() throws RestaurantNotExistException;
    List<RestaurantDTO> findAll() throws  RestaurantNotExistException;
    List<IdCateNameDTO> findAllCategoryByRestaurant(int idRes) throws CategoryNotExistException;
}
