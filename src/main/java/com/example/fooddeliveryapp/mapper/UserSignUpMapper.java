package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpMapper {
    public UserSignUpDTO userSignUpToUserSignUpDTO(UserEntity user){
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        userSignUpDTO.setFullName(user.getFullName());
        userSignUpDTO.setEmail(user.getEmail());
        userSignUpDTO.setPassword(user.getPassword());
        return userSignUpDTO;
    }
}
