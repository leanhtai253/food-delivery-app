package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.entities.DevUserEntity;
import com.example.fooddeliveryapp.repositories.DevUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevUserServiceImp implements DevUserService{
    @Autowired
    DevUserRepository devUserRepository;

    @Override
    public boolean checkEmailExists(String email) {
        List<DevUserEntity> users = devUserRepository.findAllByEmail(email);
        return users.size() > 0;
    }

    @Override
    public DevUserEntity findUserByEmail(String email) {
        List<DevUserEntity> users = devUserRepository.findAllByEmail(email);
        if (users.size() == 1) return users.get(0);
        else return null;
    }
}
