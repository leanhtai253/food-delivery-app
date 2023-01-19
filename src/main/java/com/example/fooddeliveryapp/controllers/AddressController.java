package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.AddressDTO;
import com.example.fooddeliveryapp.exceptions.NoAddressFoundException;
import com.example.fooddeliveryapp.exceptions.UnableToAddAddressException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.AddressService;
import com.example.fooddeliveryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

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

    @PostMapping("/add")
    public ResponseEntity<?> addNewAddress(@RequestBody @Valid AddressDTO address) throws UnableToAddAddressException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userService.addNewAddressForUserEmail(email, address));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(name = "id", required = true) int id) throws NoAddressFoundException {
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody @Valid AddressDTO address,
                                           @PathVariable(name = "id", required = true) int id) throws NoAddressFoundException {
        return new ResponseEntity<>(addressService.updateAddress(id, address), HttpStatus.OK);
    }

    @PostMapping("/update/default/{id}")
    public ResponseEntity<?> updateDefaultAddress(@PathVariable(name = "id", required = true) int id) throws NoAddressFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateDefaultAddress(email,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
