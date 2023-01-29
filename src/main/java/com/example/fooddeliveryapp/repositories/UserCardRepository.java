package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.UserCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardRepository extends JpaRepository<UserCardEntity, Integer> {
    List<UserCardEntity> findByUserId(int user);

}
