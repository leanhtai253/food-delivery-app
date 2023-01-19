package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findById(int id);
    List<CategoryEntity> findAll();
}
