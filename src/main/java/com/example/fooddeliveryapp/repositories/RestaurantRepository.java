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

    @Query(value = "select c.id, c.name from food as f join category as c on f.id_category = c.id where f.id_restaurant = :idRes", nativeQuery = true)
    List<Object[]> getCategoryByRestaurant(int idRes);

//    @Query(value = "select f.* from food as f join category as c on f.id_category = c.id where c.id = :idCate and f.id_restaurant = c.id", nativeQuery = true)
//    List<Object[]> getAllFoodByCategory(int idCate);

    RestaurantEntity findById(int id);
}
