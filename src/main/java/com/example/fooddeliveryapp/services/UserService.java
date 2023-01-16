package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserDTO;

public interface UserService {
    public boolean checkEmailExists(String email);
    public UserDTO findUserByEmail(String email);
}
