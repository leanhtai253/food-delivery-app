package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.mapper.UserSignUpMapper;
import com.example.fooddeliveryapp.payload.request.SignUpRequest;
import com.example.fooddeliveryapp.payload.response.ResponseError;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSignUpMapper userSignUpMapper;

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

    @Override
    public UserSignUpDTO findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
            userSignUpDTO = userSignUpMapper.userSignUpToUserSignUpDTO(user);
            return userSignUpDTO;
        }
    }



