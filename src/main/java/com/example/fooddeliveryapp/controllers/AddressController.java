package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAddress() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        System.out.println("Email: " + email);
        List<AddressDTO> addresses = userService.getAddressesByUserEmail(email);

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(addresses);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/default-address")
    public ResponseEntity<?> getDefaultAddress() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AddressDTO address = userService.getUserDefaultAddress(email);

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(address);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
