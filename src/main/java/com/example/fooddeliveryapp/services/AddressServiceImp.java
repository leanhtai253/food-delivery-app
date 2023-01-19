package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.mapper.AddressMapper;
import com.example.fooddeliveryapp.repositories.AddressRepository;
import com.example.fooddeliveryapp.utils.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImp implements AddressService{
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressUtils addressUtils;

    @Override
    public boolean checkAddressExistsById(int id) throws NoAddressFoundException {
        UserAddressEntity address = addressRepository.findById(id);
        if (address != null) return true;
        else throw new NoAddressFoundException(addressUtils.MSG_NO_ADDRESS_FOUND_ID(id));
    }

    @Override
    public void deleteAddressById(int id) throws NoAddressFoundException {
        if (checkAddressExistsById(id)) {
            addressRepository.deleteById(id);
            return;
        }
    }

    @Override
    public AddressDTO updateAddress(int id, AddressDTO address) throws NoAddressFoundException {
        if (checkAddressExistsById(id)) {
            UserAddressEntity entity = addressRepository.findById(id);
            entity.setStreet(address.getStreet());
            entity.setState(address.getState());
            entity.setCity(address.getCity());
            entity.setAddressType(addressUtils.setAddressTypeInt(address.getAddressType()));

            UserAddressEntity result = addressRepository.save(entity);
            if (result != null) {
                return addressMapper.addressToAddressDto(result);
            }
            else throw new EmptyResultDataAccessException("Unable to update address with id " + id,1);
        } else {
            throw new NoAddressFoundException(addressUtils.MSG_NO_ADDRESS_FOUND_ID(id));
        }
    }

}
