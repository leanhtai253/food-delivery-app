package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByEmail(String email);
}
