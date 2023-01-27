package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
   @Query(value = "select r.* from restaurant as r where r.name like CONCAT('%',:name,'%')", nativeQuery = true)
    List<RestaurantEntity> searchByRestaurantName(@Param("name") String name);
}
