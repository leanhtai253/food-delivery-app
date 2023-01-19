package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.exceptions.UnableToAddAddressException;

import java.util.List;

public interface UserService {
    public boolean checkEmailExists(String email);
    public UserDTO findUserByEmail(String email);
    public List<AddressDTO> getAddressesByUserEmail(String email);
    public AddressDTO getUserDefaultAddress(String email);
    public AddressDTO addNewAddressForUserEmail(String email, AddressDTO address) throws UnableToAddAddressException;
    public void updateDefaultAddress(String email, int id) throws NoAddressFoundException;
}
