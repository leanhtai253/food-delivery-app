package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.entities.UserFoodFavEntity;
import com.example.fooddeliveryapp.entities.UserResFavEntity;
import com.example.fooddeliveryapp.mapper.UserFoodFavMapper;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/favourites")
@CrossOrigin
public class FavouritesController {
    @Autowired
    FavouriteService favouriteService;

    @Autowired
    UserFoodFavMapper userFoodFavMapper;

    @PostMapping("/food/modify")
    public ResponseEntity<?> modifyFavFoodList(@RequestBody UserFoodFavEntity modification){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(favouriteService.modifyFavFoodList(modification));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @GetMapping("/food/show")
    public ResponseEntity<?> showAllFavFood(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(favouriteService.showUserFavFoodList(email));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @PostMapping("restaurant/modify")
    public ResponseEntity<?> modifyFavRestaurantsList(@RequestBody UserResFavEntity modification){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(favouriteService.modifyFavRestaurantList(modification));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @GetMapping("restaurant/show")
    public ResponseEntity<?> showAllFavRestaurants(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(favouriteService.showUserFavRestaurantList(email));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }
}
