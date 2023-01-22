package com.example.fooddeliveryapp.repositories;

import com.example.fooddeliveryapp.entities.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Integer> {
}
