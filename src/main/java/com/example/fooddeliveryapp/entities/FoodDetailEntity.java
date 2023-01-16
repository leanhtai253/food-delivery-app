package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "food_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDetailEntity {

    @Id
    @Column(name = "id_food")
    private int idFood;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "rating")
    private float rating;

    @OneToOne()
    @JoinColumn(name = "id_food")
    @JsonIgnore
    private FoodEntity food;
}
