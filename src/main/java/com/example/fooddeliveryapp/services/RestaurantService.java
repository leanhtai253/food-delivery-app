package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.dto.RestaurantDetailDto;
import com.example.fooddeliveryapp.exceptions.RestaurantNotExistException;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> getTop6Restaurants() throws RestaurantNotExistException;
    List<RestaurantDTO> findAll() throws  RestaurantNotExistException;
    RestaurantDetailDto getRestaurantDetail(int id);
}