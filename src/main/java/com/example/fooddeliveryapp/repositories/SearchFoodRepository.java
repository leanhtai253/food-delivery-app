package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchFoodRepository extends JpaRepository<FoodEntity, Integer> {
   @Query(value = "select f.* from food as f where f.name like CONCAT('%',:name,'%')", nativeQuery = true)
    List<FoodEntity> searchByFoodName(@Param("name") String name);

}
