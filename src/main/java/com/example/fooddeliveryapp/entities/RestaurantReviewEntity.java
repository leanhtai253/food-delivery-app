package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "restaurant_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "rate")
    private float rate;

    @ManyToOne()
    @JoinColumn(name = "id_restaurant")
    @JsonIgnore
    private RestaurantEntity restaurant;
}