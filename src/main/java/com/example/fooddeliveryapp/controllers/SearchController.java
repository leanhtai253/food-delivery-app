package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.SearchFoodDTO;
import com.example.fooddeliveryapp.dto.SearchRestaurantDTO;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/food/{name}")
    public ResponseEntity<?> getListAllFoodByName(@PathVariable(name = "name") String name) {
        List<SearchFoodDTO> foods = searchService.searchByFoodName(name);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(foods);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{name}")
    public ResponseEntity<?> getListAllResByName(@PathVariable(name = "name") String name) {
        List<SearchRestaurantDTO> restaurants = searchService.searchByRestaurantName(name);
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(restaurants);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}
