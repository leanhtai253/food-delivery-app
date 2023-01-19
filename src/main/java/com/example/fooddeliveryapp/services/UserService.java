package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.exceptions.UnableToAddAddressException;

import java.util.List;

public interface UserService {
    boolean checkEmailExists(String email);

    UserDTO findUserByEmail(String email);

    UserSignUpDTO findByEmail(String email);

    AddressDTO getUserDefaultAddress(String email);

    AddressDTO addNewAddressForUserEmail(String email, AddressDTO address) throws UnableToAddAddressException;

    void updateDefaultAddress(String email, int id) throws NoAddressFoundException;

    List<AddressDTO> getAddressesByUserEmail(String email);
}