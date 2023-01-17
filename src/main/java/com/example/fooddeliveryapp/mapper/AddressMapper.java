package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.utils.Constant;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public AddressDTO addressToAddressDto(UserAddressEntity entity) {
        AddressDTO dto = new AddressDTO();
        dto.setNumber(entity.getId());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());

        if (entity.getAddressType() == 1) {
            dto.setAddressType(Constant.ADDRESS_TYPE.Home.toString());
        } else {
            dto.setAddressType(Constant.ADDRESS_TYPE.Work.toString());
        }
        return dto;
    }
}
