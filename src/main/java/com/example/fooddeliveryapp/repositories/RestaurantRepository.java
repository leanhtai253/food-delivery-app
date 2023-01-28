package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
    @Query(value = "select r.* from restaurant as r order by r.id DESC limit 6", nativeQuery = true)
    List<RestaurantEntity> getTop6Restaurants();

    List<RestaurantEntity> findAll();

    RestaurantEntity findById(int id);
}
