package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.OrderUpComingDTO;

import java.util.List;

public interface OrderService {
    List<OrderUpComingDTO> getOrderUpComing(String email);
}
