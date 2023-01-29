package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "restaurant_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetailEntity {

    @Id
    @Column(name = "id_restaurant")
    private int idRestaurant;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "description")
    private String description;

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "rating")
    private float rating;

    @OneToOne()
    @JoinColumn(name = "id_restaurant")
    @JsonIgnore
    private RestaurantEntity restaurant;
}