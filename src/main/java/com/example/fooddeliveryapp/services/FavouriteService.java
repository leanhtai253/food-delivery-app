package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserFoodFavDTO;
import com.example.fooddeliveryapp.dto.UserResFavDTO;
import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import com.example.fooddeliveryapp.entities.UserResFavEntity;

import java.util.List;

public interface FavouriteService {

    List<UserFoodFavDTO> modifyFavFoodList(UserFoodFavEntity modification);
    List<UserFoodFavEntity> showUserFavFoodList(UserFoodFavEntity modification);

    List<UserFoodFavDTO> showUserFavFoodList(String email);

    List<UserResFavDTO> modifyFavRestaurantList(UserResFavEntity modification);
    List<UserResFavEntity> showUserFavRestaurantList(UserResFavEntity modification);

    List<UserResFavDTO> showUserFavRestaurantList(String email);
}
