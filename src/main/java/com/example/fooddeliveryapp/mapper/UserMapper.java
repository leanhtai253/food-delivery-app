package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
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

    public UserEntity userDTO2User(UserDTO userDTO){
        UserEntity user = new UserEntity();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setVerifyCode(userDTO.getVerifyCode());
        user.setVerifyCodeExpired(userDTO.getVerifyCodeExpired());
        user.setActive(userDTO.isActive());
        return user;
    }

    public UserSignUpDTO userSignUpToUserSignUpDTO(UserEntity user){
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        userSignUpDTO.setFullName(user.getFullName());
        userSignUpDTO.setEmail(user.getEmail());
        userSignUpDTO.setPassword(user.getPassword());
        return userSignUpDTO;
    }
}
