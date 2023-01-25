package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.UserCardDTO;
import com.example.fooddeliveryapp.entities.UserCardEntity;
import com.example.fooddeliveryapp.mapper.UserCardMapper;
import com.example.fooddeliveryapp.repositories.UserCardRepository;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCardServiceImp implements UserCardService {

    @Autowired
    UserCardRepository userCardRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCardMapper userCardMapper;

    @Override
    public List<UserCardDTO> getAllCard() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        int user = userRepository.findByEmail(email).getId();
        List<UserCardEntity> cardEntities = userCardRepository.findByUserId(user);
        List<UserCardDTO> cardDTOS = new ArrayList<>();
        for (UserCardEntity card: cardEntities) {
            cardDTOS.add(userCardMapper.cardToCardDto(card));
        }
        return cardDTOS;
    }
}
