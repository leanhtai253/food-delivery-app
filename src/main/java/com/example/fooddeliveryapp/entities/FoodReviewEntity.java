package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "food_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "rate")
    private float rate;

    @ManyToOne()
    @JoinColumn(name = "id_food")
    @JsonIgnore
    private FoodEntity food;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private UserEntity user;
}