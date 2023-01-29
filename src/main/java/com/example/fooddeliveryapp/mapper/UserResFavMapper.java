package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserResFavDTO;
import com.example.fooddeliveryapp.entities.UserResFavEntity;
import org.springframework.stereotype.Service;

@Service
public class UserResFavMapper {

    public UserResFavDTO userResFav2UserResFavDTO (UserResFavEntity entity){
        UserMapper userMapper = new UserMapper();
        RestaurantMapper restaurantMapper = new RestaurantMapper(); //reuse mapper to change entity -> dto
        UserResFavDTO dto = new UserResFavDTO();                    //initialize

        dto.setIdUser(entity.getIdUser());
        dto.setIdRestaurant(entity.getIdRestaurant());
        dto.setUserDTO(userMapper.userToUserDTO(entity.getUser()));
        dto.setRestaurantDTO(restaurantMapper.restaurantToRestaurantDTO(entity.getRestaurant()));
        return dto;
    }

}
