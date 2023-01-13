package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private float price;

    @Column(name = "area")
    private String area;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne()
    @JoinColumn(name = "id_restaurant")
    @JsonIgnore
    private RestaurantEntity restaurant;

    @OneToOne(mappedBy = "food")
    private FoodDetailEntity foodDetail;

    @OneToMany(mappedBy = "food")
    private Set<FoodReviewEntity> foodReviews;

    @OneToMany(mappedBy = "food")
    private Set<FoodAddOnEntity> foodAddOns;

    @OneToMany(mappedBy = "food")
    private Set<FoodMaterialEntity> foodMaterials;

    @OneToMany(mappedBy = "food")
    private Set<OrderDetailEntity> orderDetails;

    @OneToMany(mappedBy = "food")
    private Set<UserFoodFavEntity> userFoodFavs;
}