package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.dto.UserCardDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.exceptions.UnableToAddAddressException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.repositories.UserRepository;
import com.example.fooddeliveryapp.services.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
public class UserCardController {

    @Autowired
    UserCardService userCardService;

    @GetMapping("")
    public ResponseEntity<?> getAllCards() {
        List<UserCardDTO> cards = userCardService.getAllCard();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(cards);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestBody @Valid UserCardDTO card) {
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userCardService.addCard(card));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
