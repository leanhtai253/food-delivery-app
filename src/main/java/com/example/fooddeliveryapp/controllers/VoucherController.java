package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.VoucherDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;
    @GetMapping("/all")
    public ResponseEntity<Object> getListings () {
        List<VoucherDTO> vouchers = voucherService.getAllVouchers();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(vouchers);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
