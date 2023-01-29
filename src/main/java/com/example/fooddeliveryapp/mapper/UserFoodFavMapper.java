package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.FoodDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserFoodFavDTO;
import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import org.springframework.stereotype.Service;

@Service
public class UserFoodFavMapper {

    public UserFoodFavDTO userFoodFav2UserFoodFavDTO (UserFoodFavEntity entity){
        UserMapper userMapper = new UserMapper();
        FoodMapper foodMapper = new FoodMapper();          //reuse mapper to change from entity -> DTO
        UserFoodFavDTO dto = new UserFoodFavDTO();         //initialize

        dto.setIdUser(entity.getIdUser());
        dto.setIdFood(entity.getIdFood());
        dto.setUserDTO(userMapper.userToUserDTO(entity.getUser()));
        dto.setFoodDTO(foodMapper.foodToFoodDto(entity.getFood()));

        return dto;
    }

    public UserFoodFavEntity userFoodFavDTO2UserFoodFav (UserFoodFavDTO dto){
        UserMapper userMapper = new UserMapper();
        FoodMapper foodMapper = new FoodMapper();          //reuse mapper to change from entity -> DTO
        UserFoodFavEntity entity = new UserFoodFavEntity();         //initialize

        entity.setIdUser(dto.getIdUser());
        entity.setIdFood(dto.getIdFood());
        entity.setUser(userMapper.userDTO2User(dto.getUserDTO()));
        entity.setFood(foodMapper.foodDTO2Food(dto.getFoodDTO()));

        return entity;
    }
}
