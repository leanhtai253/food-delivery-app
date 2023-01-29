package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFoodFavRepository extends JpaRepository<UserFoodFavEntity,Integer> {
    List<UserFoodFavEntity> findAllByIdUser(int idUser);

}
