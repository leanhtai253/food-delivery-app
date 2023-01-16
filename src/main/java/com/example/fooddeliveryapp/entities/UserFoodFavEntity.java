package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.entities.id.UserFoodFavId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_food_fav")
@IdClass(UserFoodFavId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFoodFavEntity {
    @Id
    private int id_user;

    @Id
    private int id_food;

    @ManyToOne()
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    @JsonIgnore
    private FoodEntity food;
}