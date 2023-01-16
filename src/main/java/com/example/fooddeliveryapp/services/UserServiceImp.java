package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean checkEmailExists(String email) {
        List<UserEntity> users = userRepository.findAllByEmail(email);
        return users.size() > 0;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        List<UserEntity> users = userRepository.findAllByEmail(email);
        if (users.size() == 1) {
            UserDTO userDTO = new UserDTO();
            userDTO = userMapper.userToUserDTO(users.get(0));
            return userDTO;
        }
        else return null;
    }
}
