package com.example.fooddeliveryapp.dto;

import com.example.fooddeliveryapp.entities.OrderDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderPreviousDTO {
    private int id;
    private String status;
    private float totalPrice;
    private float rating;
    private List<String> foodList;
}
