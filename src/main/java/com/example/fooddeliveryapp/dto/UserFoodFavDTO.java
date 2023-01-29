package com.example.fooddeliveryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFoodFavDTO {
    private int idUser;
    private int idFood;
    private UserDTO userDTO;
    private FoodDTO foodDTO;

}
