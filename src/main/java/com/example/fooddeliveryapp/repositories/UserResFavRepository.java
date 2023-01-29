package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import com.example.fooddeliveryapp.entities.UserResFavEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResFavRepository extends JpaRepository<UserResFavEntity,Integer> {
    List<UserResFavEntity> findAllByIdUser(int idUser);

}
