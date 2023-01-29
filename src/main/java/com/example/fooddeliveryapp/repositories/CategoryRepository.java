package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.CategoryEntity;
import com.example.fooddeliveryapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "select c.* from category as c order by c.id ASC limit 6", nativeQuery = true)
    List<CategoryEntity> getTop6Categories();

    List<CategoryEntity> findAll();

    CategoryEntity findById(int id);

    @Query(value = "select c.id, c.name from category as c inner join food as f on f.id_category = c.id where f.id_restaurant = :idRes", nativeQuery = true)
    List<Object[]> findAllByResId(int idRes);
}
