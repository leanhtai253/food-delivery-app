package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import com.example.fooddeliveryapp.exceptions.FoodNotExistException;
import com.example.fooddeliveryapp.mapper.FoodMapper;
import com.example.fooddeliveryapp.repositories.CategoryRepository;
import com.example.fooddeliveryapp.repositories.FoodRepository;
import com.example.fooddeliveryapp.repositories.RestaurantRepository;
import com.example.fooddeliveryapp.utils.FoodSorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService{
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<FoodViewDTO> sortFoods(List<FoodViewDTO> foods, String sortBy, String price) {
        FoodSorting foodSorting = new FoodSorting();
        boolean priceAsc = false;
        if (price.equals("asc")) priceAsc = true;

        if (sortBy.equals("rating")) {
            if (!price.equals("")) {
                return foodSorting.sortFoodViewDtosByRatingThenPrice(foods, priceAsc);
            }
            return foodSorting.sortFoodViewDtosByRating(foods);
        } else if (sortBy.equals("popularity")) {
            if (!price.equals("")) {
                return foodSorting.sortFoodViewDtosByPopularityThenPrice(foods, priceAsc);
            }
            return foodSorting.sortFoodViewDtosByPopularity(foods);
        } else {
            if (!price.equals("")) {
                return foodSorting.sortFoodViewDtosByPrice(foods, priceAsc);
            }
            return foods;
        }
    }

    @Override
    public List<FoodViewDTO> getAllFoods() {
        List<FoodEntity> foodEntities = foodRepository.findAll();
        List<FoodViewDTO> foods = new ArrayList<>();
        for (FoodEntity food : foodEntities) {
            foods.add(foodMapper.foodToFoodViewDto(food));
        }

        return foods;
    }

    @Override
    public List<FoodDTO> getTop6FoodByArea(String area) {
        List<FoodEntity> foodEntities = foodRepository.findTop6ByArea(area);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for (FoodEntity food: foodEntities) {
            foodDTOS.add(foodMapper.foodToFoodDto(food));
        }
        return foodDTOS;
    }

    @Override
    public List<FoodDTO> getAllFoodsByArea(String area) {
        List<FoodEntity> foodEntities = foodRepository.findAllByArea(area);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for (FoodEntity food: foodEntities) {
            foodDTOS.add(foodMapper.foodToFoodDto(food));
        }
        return foodDTOS;
    }

//    @Override
//    public List<FoodDTO> getAllFoodsByCategory(String category) {
//        List<FoodEntity> foodEntities = foodRepository.findALlByCategory(category);
//        List<FoodDTO> foodDTOS = new ArrayList<>();
//        for (FoodEntity food : foodEntities){
//            foodDTOS.add(foodMapper.foodToFoodDto(food));
//        }
//        return foodDTOS;
//    }

    @Override
    public List<FoodViewDTO> findAllByCategoryAndRestaurant(int idCate, int idRes) throws FoodNotExistException {
        CategoryEntity cate = categoryRepository.findById(idCate);
        RestaurantEntity res = restaurantRepository.findById(idRes);
        List<FoodEntity> food = foodRepository.findAllByCategoryAndRestaurant(cate,res);
        List<FoodViewDTO> dtos = new ArrayList<>();
        for (FoodEntity entity : food){
            dtos.add(foodMapper.foodToFoodViewDto(entity));
        }
        return dtos;
    }

}
