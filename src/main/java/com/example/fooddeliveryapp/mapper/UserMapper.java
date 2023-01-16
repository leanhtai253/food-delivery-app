package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.entities.DevUser;
import com.example.fooddeliveryapp.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO userToUserDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setVerifyCode(user.getVerifyCode());
        userDTO.setVerifyCodeExpired(user.getVerifyCodeExpired());
        userDTO.setActive(user.isActive());
        return userDTO;
    }
}
