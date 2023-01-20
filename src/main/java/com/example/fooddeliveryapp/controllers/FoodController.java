package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.FoodDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping("/t6/{area}")
    public ResponseEntity<?> getListTop6FoodByArea(@PathVariable(name = "area") String area) {
        List<FoodDTO> foods = foodService.getTop6FoodByArea(area);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(foods);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/all/{area}")
    public ResponseEntity<?> getListAllFoodByArea(@PathVariable(name = "area") String area) {
        List<FoodDTO> foods = foodService.getAllFoodsByArea(area);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(foods);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
