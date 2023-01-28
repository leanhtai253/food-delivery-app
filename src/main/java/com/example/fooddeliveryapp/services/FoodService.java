package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodChoiceDTO;
import com.example.fooddeliveryapp.dto.FoodDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.exceptions.FoodNotExistException;

import java.util.List;

public interface FoodService {
    List<FoodViewDTO> sortFoods(List<FoodViewDTO> foods, String sortBy, String price);
    List<FoodViewDTO> getAllFoods();
    List<FoodDTO> getTop6FoodByArea(String area);
    List<FoodDTO> getAllFoodsByArea(String area);
    List<FoodViewDTO> getAllFoodByCategoryAndRestaurant(int idCate, int idRes) throws FoodNotExistException;
    List<FoodChoiceDTO> getAllFoodDetailById(int id) throws FoodNotExistException;
}
