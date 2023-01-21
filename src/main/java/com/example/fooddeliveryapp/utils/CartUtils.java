package com.example.fooddeliveryapp.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CartUtils {
    private String MSG_UNABLE_TO_ADD_ITEM = "Unable to add items to cart";
    private String MSG_UNABLE_TO_REMOVE_ITEM = "Unable to remove items from cart";
    private String MSG_UNABLE_TO_CLEAR_CART = "Unable to clear cart";
}
