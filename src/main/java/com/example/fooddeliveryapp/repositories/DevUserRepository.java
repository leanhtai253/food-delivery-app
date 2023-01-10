package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.DevUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevUserRepository extends JpaRepository<DevUserEntity, Integer> {
    List<DevUserEntity> findAllByEmail(String email);
}
