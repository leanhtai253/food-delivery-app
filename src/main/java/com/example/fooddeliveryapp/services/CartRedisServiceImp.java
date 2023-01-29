package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.ItemDTO;
import com.example.fooddeliveryapp.exceptions.CartOperationsException;
import com.example.fooddeliveryapp.repositories.CartRedisRepository;
import com.example.fooddeliveryapp.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartRedisServiceImp implements CartRedisService{
    @Autowired
    CartRedisRepository cartRedisRepository;

    @Autowired
    CartUtils cartUtils;


    @Override
    public ItemDTO addItem(String id, ItemDTO item) throws CartOperationsException {
        try {
            cartRedisRepository.addToCart(id, item);
            return item;
        } catch (Exception e) {
            throw new CartOperationsException(cartUtils.getMSG_UNABLE_TO_ADD_ITEM());
        }
    }

    @Override
    public List getCart(String id) {
        return cartRedisRepository.getCart(id);
    }

    @Override
    public ItemDTO removeItem(String id, ItemDTO item) throws CartOperationsException {
        try {
            cartRedisRepository.removeItem(id, item);
            return item;
        } catch (Exception e) {
            throw new CartOperationsException(cartUtils.getMSG_UNABLE_TO_REMOVE_ITEM());
        }
    }

    @Override
    public void clearCart(String id) throws CartOperationsException {
        if (cartRedisRepository.clearCart(id)) {
            return;
        } else {
            throw new CartOperationsException(cartUtils.getMSG_UNABLE_TO_CLEAR_CART());
        }
    }
}
