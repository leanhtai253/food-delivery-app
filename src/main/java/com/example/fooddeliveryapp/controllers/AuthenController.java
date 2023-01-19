package com.example.fooddeliveryapp.controllers;

import com.example.fooddeliveryapp.configurations.security.JwtUtil;
import com.example.fooddeliveryapp.dto.UserSignUpDTO;
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
import org.springframework.ui.Model;
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

    public static final Logger log = LoggerFactory.getLogger(AuthenController.class);

//    @GetMapping("/sign-up")
//    public String signUp(@ModelAttribute UserSignUpDTO userSignUpDTO, Model model) {
//        model.addAttribute("userSignUpDTO", userSignUpDTO);
//        return "signup";
//    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        if (userService.checkEmailExists(request.getEmail())) {
            throw new BadCredentialsException("Email already exist");
        } else {
            UserSignUpDTO userSignUpDTO = userService.findByEmail(request.getEmail());
            userSignUpDTO.setFullName(request.getFullName());
            userSignUpDTO.setEmail(request.getEmail());
            return new ResponseEntity<>(userSignUpDTO, HttpStatus.OK);
        }
    }
}
