package com.example.fooddeliveryapp.entities;

import javax.persistence.*;

// This class is only viable in development mode and will be removed after official entities are created
@Entity(name="DevUserEntity")
@Table(name="user")
public class DevUserEntity {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCodeExpired() {
        return verifyCodeExpired;
    }

    public void setVerifyCodeExpired(String verifyCodeExpired) {
        this.verifyCodeExpired = verifyCodeExpired;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
