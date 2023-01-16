package com.example.fooddeliveryapp.configurations.providers;

import com.example.fooddeliveryapp.entities.DevUserEntity;
import com.example.fooddeliveryapp.payload.response.ResponseError;
import com.example.fooddeliveryapp.services.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    DevUserService devUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        System.out.println("Arrived at custom provider. " + email + " " + password);
        if (devUserService.checkEmailExists(email)) {
            DevUserEntity user = devUserService.findUserByEmail(email);
            System.out.println("Fetched pwd: " + user.getPassword());
            if (passwordEncoder.matches(password,user.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPassword(), new ArrayList<>()
                );
                System.out.println("Password matches");
                return authenticationToken;
            } else {
                System.out.println("Password not matches");
            }
        } else {
            System.out.println("Email not exists");
            throw new BadCredentialsException("Email does not exist. Please create an account.");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
    }
}
