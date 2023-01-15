package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.entities.id.UserResFavId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_res_fav")
@IdClass(UserResFavId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResFavEntity {
    @Id
    private int id_user;

    @Id
    private int id_restaurant;

    @ManyToOne()
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "id_restaurant", insertable = false, updatable = false)
    @JsonIgnore
    private RestaurantEntity restaurant;
}
