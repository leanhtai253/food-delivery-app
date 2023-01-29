package com.example.fooddeliveryapp.entities.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFoodFavId implements Serializable {
    private int idUser;
    private int idFood;
}
