package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.exceptions.UnableToAddAddressException;
import com.example.fooddeliveryapp.mapper.AddressMapper;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.mapper.UserSignUpMapper;
import com.example.fooddeliveryapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.fooddeliveryapp.repositories.AddressRepository;
import com.example.fooddeliveryapp.utils.AddressUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSignUpMapper userSignUpMapper;
    AddressMapper addressMapper;

    @Autowired
    AddressUtils addressUtils;

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

    @Override
    public AddressDTO addNewAddressForUserEmail(String email, AddressDTO address) throws UnableToAddAddressException {
        UserEntity user = userRepository.findByEmail(email);
        UserAddressEntity newAddress = addressMapper.addressDtoToAddress(address, user);
        UserAddressEntity result =  addressRepository.save(newAddress);
        if (result != null) {
            AddressDTO resultDto = addressMapper.addressToAddressDto(result);
            return resultDto;
        }
        else throw new UnableToAddAddressException(addressUtils.MSG_UNABLE_TO_ADD_ADDRESS());
    }

    @Override
    public void updateDefaultAddress(String email, int id) throws NoAddressFoundException {
        if (addressService.checkAddressExistsById(id)) {
            UserEntity user = userRepository.findByEmail(email);
            Set<UserAddressEntity> new_addresses = new HashSet<>();
            for (UserAddressEntity addr : user.getUserAddresses()) {
                if (addr.getId() == id) {
                    for (UserAddressEntity address : user.getUserAddresses()) {
                        if (address.getId() != id && address.isDefault()) {
                            address.setDefault(false);
                        } else if (address.getId() == id) {
                            address.setDefault(true);
                        }
                        new_addresses.add(address);
                    }
                    user.setUserAddresses(new_addresses);
                    UserEntity result = userRepository.save(user);
                    if (result != null) return;
                    else throw new EmptyResultDataAccessException("Unable to update default address", 1);
                }
            }
        } else {
            throw new NoAddressFoundException(addressUtils.MSG_NO_ADDRESS_FOUND_ID(id));
        }
    }
}
