package com.example.fooddeliveryapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// This class is only viable in development mode and will be removed after official entities are created
@Entity(name="DevUserEntity")
@Table(name="user")
@Getter
@Setter
public class DevUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;

    @Column(name="fullname")
    private String fullName;

    private String token;

    @Column(name="type_token")
    private String typeToken;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="verify_code")
    private String verifyCode;
    @Column(name="verify_code_expired")
    private String verifyCodeExpired;
    @Column(name="is_active")
    boolean isActive;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
