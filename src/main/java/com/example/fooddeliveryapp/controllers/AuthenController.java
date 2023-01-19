package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.configurations.security.JwtUtil;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.exceptions.AlreadyExistException;
import com.example.fooddeliveryapp.payload.request.SignInRequest;
import com.example.fooddeliveryapp.payload.request.SignUpRequest;
import com.example.fooddeliveryapp.payload.response.ResponseError;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import com.example.fooddeliveryapp.services.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authen")
@AllArgsConstructor
public class AuthenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<?> SignIn(@Valid @RequestBody SignInRequest request) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generate(request.getEmail(), "token");
        String tokenDecoded = jwtUtil.getSubject(token);

        ResponseSuccess responseSuccess = new ResponseSuccess();
        responseSuccess.setData(token);
        responseSuccess.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) throws AlreadyExistException {
        ResponseSuccess success = new ResponseSuccess();
        if (userService.checkEmailExists(request.getEmail())){
            throw new AlreadyExistException("Email is already exist!");
//            return new ResponseEntity<>("Email is already exist!",HttpStatus.BAD_REQUEST);
        }
//        userService.addNewUser(request);
        success.setStatus(HttpStatus.OK.value());
        success.setData(userService.addNewUser(request));
        return new ResponseEntity<>(success,HttpStatus.OK);

    }
}
