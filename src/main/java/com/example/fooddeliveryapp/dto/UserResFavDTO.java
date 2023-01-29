package com.example.fooddeliveryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResFavDTO {
    private int idUser;
    private int idRestaurant;
    private UserDTO userDTO;
    private RestaurantDTO restaurantDTO;
}
