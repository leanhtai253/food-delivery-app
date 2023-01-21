package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.ItemDTO;
import com.example.fooddeliveryapp.entities.CartRedisEntity;
import com.example.fooddeliveryapp.exceptions.CartOperationsException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartRedisService {
    public ItemDTO addItem(String id, ItemDTO item) throws CartOperationsException;
    public List getCart(String id);
    public ItemDTO removeItem(String id, ItemDTO item) throws CartOperationsException;
    public void clearCart(String id) throws CartOperationsException;
}
