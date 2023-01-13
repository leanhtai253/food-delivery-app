package com.example.fooddeliveryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantReviewEntity> restaurantReviews;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodEntity> foods;

    @OneToOne(mappedBy = "restaurant")
    private RestaurantDetailEntity restaurantDetail;

    @OneToMany(mappedBy = "restaurant")
    private Set<UserResFavEntity> userResFavs;
}