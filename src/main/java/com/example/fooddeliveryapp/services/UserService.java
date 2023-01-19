package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean checkEmailExists(String email);
    UserDTO findUserByEmail(String email);
    List<AddressDTO> getAddressesByUserEmail(String email);
    AddressDTO getUserDefaultAddress(String email);
}
