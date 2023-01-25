package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserCardDTO;
import com.example.fooddeliveryapp.entities.UserCardEntity;
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
}
