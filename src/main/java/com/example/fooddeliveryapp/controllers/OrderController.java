package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.OrderPreviousDTO;
import com.example.fooddeliveryapp.dto.OrderUpComingDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/upcoming")
    public ResponseEntity<?> getListOrderUpComing() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<OrderUpComingDTO> orders = orderService.getOrderUpComing(email);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(orders);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/previous")
    public ResponseEntity<?> getListOrderPrevious(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<OrderPreviousDTO> orders = orderService.getOrderPrevious(email);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(orders);
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/previous/{id}")
    public ResponseEntity<?> getOrderPreviousDetail(@PathVariable(name = "id", required = true) int id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Object> returnData = new ArrayList<>();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setStatus(HttpStatus.OK.value());
        responseSuccess.setData(orderService.getOrderPreviousDetails(email,id));
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
