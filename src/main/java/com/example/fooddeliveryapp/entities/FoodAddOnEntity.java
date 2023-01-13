package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "food_addon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodAddOnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private float price;

    @ManyToOne()
    @JoinColumn(name = "id_food")
    @JsonIgnore
    private FoodEntity food;
}
