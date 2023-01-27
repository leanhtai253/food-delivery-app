package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserCardDTO;
import com.example.fooddeliveryapp.entities.UserAddressEntity;
import com.example.fooddeliveryapp.entities.UserCardEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserCardMapper {
    public UserCardDTO cardToCardDto(UserCardEntity entity) {
        UserCardDTO dto = new UserCardDTO();
        dto.setId(entity.getId());
        dto.setUser(entity.getUser().getId());
        dto.setCompany(entity.getCompany());
        dto.setCardType(entity.getCardType());
        dto.setLevel(entity.getLevel());
        return dto;
    }
    public UserCardEntity cardDtoToCard(UserCardDTO dto, UserEntity user) {
        UserCardEntity entity = new UserCardEntity();
        entity.setId(dto.getId());
        entity.setUser(user);
        entity.setCompany(dto.getCompany());
        entity.setCardType(dto.getCardType());
        entity.setLevel(dto.getLevel());
        return entity;
    }
}
