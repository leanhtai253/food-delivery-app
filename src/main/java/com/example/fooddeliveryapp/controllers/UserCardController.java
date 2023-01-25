package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.UserCardDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
