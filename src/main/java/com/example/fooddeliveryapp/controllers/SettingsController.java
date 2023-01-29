package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.exceptions.EmptyFieldException;
import com.example.fooddeliveryapp.mapper.UserMapper;
import com.example.fooddeliveryapp.payload.request.UpdateUserInfoRequest;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.repositories.UserRepository;
import com.example.fooddeliveryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/settings")
@CrossOrigin
public class SettingsController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/showUserInfo")
    public ResponseEntity<?> showUserInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userService.showUserInfo(email));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserInfoRequest request) throws EmptyFieldException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userService.updateUserInfo(email, request.getFullName(), request.getPhoneNumber()));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @PostMapping("/updateProfilePic")
    public ResponseEntity<?> updateProfilePic(@RequestParam("profilePic")MultipartFile profilePic){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userService.updateProfilePic(profilePic, email));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }

    @PostMapping("/deleteProfilePic")
    public ResponseEntity<?> deleteProfilePic(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(userService.deleteProfilePic(email));
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess,HttpStatus.OK);
    }
}
