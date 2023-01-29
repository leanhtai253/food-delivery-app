package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.dto.CategoryDTO;
import com.example.fooddeliveryapp.dto.CategoryWithIdDTO;
import com.example.fooddeliveryapp.dto.IdCateNameDTO;
import com.example.fooddeliveryapp.exceptions.CategoryNotExistException;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/t6")
    public ResponseEntity<?> getTop6Categories() throws CategoryNotExistException {

        List<CategoryDTO> categories = categoryService.getTop6Categoties();

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(categories);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() throws CategoryNotExistException {

        List<CategoryDTO> categories = categoryService.getAllCategories();

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(categories);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
    @GetMapping("/all/with-ids")
    public ResponseEntity<?> getAllCategoriesWithId() throws CategoryNotExistException {

        List<CategoryWithIdDTO> categories = categoryService.getAllCategoriesWithId();

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(categories);
        responseSuccess.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @GetMapping("/{idRes}")
    public ResponseEntity<?> getAllCateByRes(@PathVariable int idRes) throws CategoryNotExistException{
        List<IdCateNameDTO> dtos = categoryService.getAllCategoryByRestaurantId(idRes);
        ResponseSuccess success = new ResponseSuccess();
        success.setData(dtos);
        success.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

}
