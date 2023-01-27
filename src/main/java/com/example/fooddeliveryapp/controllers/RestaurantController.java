package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.CategoryFromRestaurantDetailDTO;
import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.dto.RestaurantDTO;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.exceptions.FoodNotExistException;
import com.example.fooddeliveryapp.exceptions.RestaurantNotExistException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/t6")
    public ResponseEntity<?> getListTop6Restaurants () throws RestaurantNotExistException {
        List<RestaurantDTO> restaurants = restaurantService.getTop6Restaurants();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(restaurants);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getListAllRestaurants() throws RestaurantNotExistException{
        List<RestaurantDTO> restaurants = restaurantService.findAll();

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(restaurants);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/cate/{idRes}")
    public ResponseEntity<?> getAllCateByRes(@PathVariable int idRes) throws CategoryNotExistException{
        List<IdCateNameDTO> dtos = restaurantService.findAllCategoryByRestaurant(idRes);
        ResponseSuccess success = new ResponseSuccess();
        success.setData(dtos);
        success.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(success,HttpStatus.OK);
    }


}
