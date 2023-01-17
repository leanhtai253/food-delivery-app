package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.mapper.AddressMapper;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

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
    public List<AddressDTO> getAddressesByUserEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        List<AddressDTO> addresses = new ArrayList<>();
        if (user != null) {
            user.getUserAddresses().forEach((address) -> {
                addresses.add(addressMapper.addressToAddressDto(address));
            });
            return addresses;
        }
        return null;
    }

    @Override
    public AddressDTO getUserDefaultAddress(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            for (UserAddressEntity address : user.getUserAddresses()) {
                if (address.isDefault()) {
                    return addressMapper.addressToAddressDto(address);
                }
            }
            return null;
        }
        return null;
    }
}
