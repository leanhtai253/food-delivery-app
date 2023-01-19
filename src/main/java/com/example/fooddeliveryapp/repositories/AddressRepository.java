package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<UserAddressEntity, Integer> {
    public UserAddressEntity findById(int id);
}
