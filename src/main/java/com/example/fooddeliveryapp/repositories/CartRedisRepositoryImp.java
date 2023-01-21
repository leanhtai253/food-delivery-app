package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRedisRepositoryImp implements CartRedisRepository{
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void addToCart(String id, Object item) {
        // Check if item exists in the cart already
        ItemDTO itemToAdd = (ItemDTO) item;
        List<ItemDTO> currentItems = getCart(id);
        int index = 0;
        for (ItemDTO currentItem : currentItems) {
            if (itemToAdd.getProductId() == currentItem.getProductId()) {
                int qty = itemToAdd.getQuantity();
                itemToAdd.setQuantity(qty + currentItem.getQuantity());
                currentItems.set(index, itemToAdd);
                Long indexInRedis = redisTemplate.opsForList().indexOf(id, currentItem);
                redisTemplate.opsForList().set(id, indexInRedis, itemToAdd);
                return;
            }
            index++;
        }
        redisTemplate.opsForList().rightPush(id, item);
    }

    @Override
    public List getCart(String id) {
        Long end = redisTemplate.opsForList().size(id);
        return redisTemplate.opsForList().range(id, 0, end-1);
    }

    @Override
    public void removeItem(String id, Object item) {
        redisTemplate.opsForList().remove(id, -1, item);
    }

    @Override
    public boolean clearCart(String id) {
        return redisTemplate.delete(id);
    }
}
