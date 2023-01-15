package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.DevUserDTO;
import com.example.fooddeliveryapp.entities.DevUser;
import org.springframework.stereotype.Service;

@Service
public class DevUserMapper {
    public DevUserDTO devUsertoDevUserDTO(DevUser devUser){
        DevUserDTO devUserDTO = new DevUserDTO();
        devUserDTO.setEmail(devUser.getEmail());
        devUserDTO.setPassword(devUser.getPassword());
        devUserDTO.setFullName(devUserDTO.getFullName());
        devUserDTO.setPhoneNumber(devUser.getPhoneNumber());
        devUserDTO.setVerifyCode(devUser.getVerifyCode());
        devUserDTO.setVerifyCodeExpired(devUser.getVerifyCodeExpired());
        devUserDTO.setActive(devUser.isActive());
        return devUserDTO;
    }
}
