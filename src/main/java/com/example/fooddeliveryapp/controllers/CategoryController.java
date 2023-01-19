package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() throws CategoryNotExistException {

        List<CategoryDTO> categories = categoryService.getAllCategories();

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(categories);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

}
