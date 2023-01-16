package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean checkEmailExists(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public UserDTO findUserByEmail(String email){
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO = userMapper.userToUserDTO(user);
            return userDTO;
        }
        else return null;
    }
}
