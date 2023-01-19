package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressService {
    public boolean checkAddressExistsById(int id) throws NoAddressFoundException;
    public void deleteAddressById(int id) throws NoAddressFoundException;
    public AddressDTO updateAddress(int id, AddressDTO address) throws NoAddressFoundException;
}
