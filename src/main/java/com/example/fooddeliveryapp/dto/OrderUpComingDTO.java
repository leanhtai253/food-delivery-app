package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpComingDTO {
    private int id;
    private String status;
    private String estimateShip;
}
