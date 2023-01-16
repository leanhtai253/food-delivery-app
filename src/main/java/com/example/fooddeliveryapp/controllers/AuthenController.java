package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.configurations.security.JwtUtil;
import com.example.fooddeliveryapp.payload.request.SignInRequest;
import com.example.fooddeliveryapp.payload.response.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authen")
public class AuthenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

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
}
