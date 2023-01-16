package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    public CategoryEntity findById(int id);
}
