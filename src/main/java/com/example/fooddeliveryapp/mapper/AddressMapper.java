package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.utils.AddressUtils;
import com.example.fooddeliveryapp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    @Autowired
    AddressUtils addressUtils;

    public AddressDTO addressToAddressDto(UserAddressEntity entity) {
        AddressDTO dto = new AddressDTO();
        dto.setNumber(entity.getId());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setAddressType(addressUtils.setAddressTypeString(entity.getAddressType()));
        return dto;
    }

    public UserAddressEntity addressDtoToAddress(AddressDTO dto, UserEntity user) {
        UserAddressEntity entity = new UserAddressEntity();
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setDefault(false);
        entity.setAddressType(addressUtils.setAddressTypeInt(dto.getAddressType()));
        entity.setUser(user);

        return entity;
    }
}
