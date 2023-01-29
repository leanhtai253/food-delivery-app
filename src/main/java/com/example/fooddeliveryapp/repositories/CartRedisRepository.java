package com.example.fooddeliveryapp.repositories;

import java.util.List;

public interface CartRedisRepository {
    public void addToCart(String id, Object item);
    public List getCart(String id);
    public void removeItem(String id, Object item);
    public boolean clearCart(String id);
}
