package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.dto.ItemDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


@Getter
@RedisHash("cart")
public class CartRedisEntity implements Serializable {
    private int userId;
    private List<ItemDTO> items;
    private float price;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public void calculateCartPrice() {
        float price = 0;
        for (ItemDTO item : this.getItems()) {
            price += item.getPrice() * item.getQuantity();
        }
        this.price = price;
    }

}
