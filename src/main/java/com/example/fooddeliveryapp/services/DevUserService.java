package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entities.DevUserEntity;
import org.springframework.stereotype.Service;

public interface DevUserService {
    public boolean checkEmailExists(String email);
    public DevUserEntity findUserByEmail(String email);
}
