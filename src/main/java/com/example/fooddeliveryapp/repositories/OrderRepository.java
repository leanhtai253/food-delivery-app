package com.example.fooddeliveryapp.repositories;


import com.example.fooddeliveryapp.entities.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<TOrderEntity, Integer> {
}
