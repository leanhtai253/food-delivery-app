package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.CategoryService;
import com.example.fooddeliveryapp.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/listings/sort")
public class FoodSortController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    FoodService foodService;

    @GetMapping("/{category}")
    public ResponseEntity<Object> getListingsByCategory (@RequestParam("sortBy") String sortBy,
                                               @RequestParam("price") String price,
                                               @PathVariable("category") int category) {

        List<FoodViewDTO> foods = categoryService.getFoodsWithinCategory(category);
        foods = foodService.sortFoods(foods, sortBy, price);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(foods);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getListings (@RequestParam("sortBy") String sortBy,
                                               @RequestParam("price") String price) {
        List<FoodViewDTO> foods = foodService.getAllFoods();
        foods = foodService.sortFoods(foods, sortBy, price);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(foods);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}
