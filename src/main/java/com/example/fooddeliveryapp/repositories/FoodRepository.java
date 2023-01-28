package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findTop6ByArea(String area);
    List<FoodEntity> findAllByArea(String area);
    List<FoodEntity> findAllByCategoryAndRestaurant(CategoryEntity cate, RestaurantEntity res);
    List<FoodEntity> findById(int id);

}
