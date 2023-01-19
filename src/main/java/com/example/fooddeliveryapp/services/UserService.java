package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.payload.request.SignUpRequest;

public interface UserService {
     boolean checkEmailExists(String email);
     UserDTO findUserByEmail(String email);
     UserSignUpDTO findByEmail(String email);

}
