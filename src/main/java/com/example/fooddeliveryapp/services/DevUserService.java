package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entities.DevUserEntity;

public interface DevUserService {
    public boolean checkEmailExists(String email);
    public DevUserEntity findUserByEmail(String email);
}
