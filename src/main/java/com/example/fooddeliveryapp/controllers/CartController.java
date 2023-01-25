package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.ItemDTO;
import com.example.fooddeliveryapp.exceptions.CartOperationsException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.CartRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartRedisService cartRedisService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody @Valid ItemDTO item) throws CartOperationsException {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(cartRedisService.addItem(email,item));
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/view-cart")
    public ResponseEntity<?> viewCart() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(cartRedisService.getCart(email));
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeItem(@RequestBody @Valid ItemDTO item) throws CartOperationsException {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(cartRedisService.removeItem(email, item));
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearCart() throws CartOperationsException {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        cartRedisService.clearCart(email);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
