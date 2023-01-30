package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TOrderRepository extends JpaRepository<TOrderEntity, Integer> {
    public TOrderEntity findById(int id);
}