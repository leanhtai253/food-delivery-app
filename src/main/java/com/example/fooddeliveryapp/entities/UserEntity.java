package com.example.fooddeliveryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "token")
    private String token;

    @Column(name = "type_token")
    private String typeToken;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "verify_code")
    private String verifyCode;

    @Column(name = "verify_code_expired")
    private String verifyCodeExpired;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "user")
    private UserDetailEntity userDetail;

    @OneToMany(mappedBy = "user")
    private Set<UserCardEntity> userCards;

    @OneToMany(mappedBy = "user")
    private Set<FoodReviewEntity> foodReviews;

    @OneToMany(mappedBy = "user")
    private Set<TOrderEntity> tOrders;

    @OneToMany(mappedBy = "user")
    private Set<UserAddressEntity> userAddresses;

    @OneToMany(mappedBy = "user")
    private Set<UserFoodFavEntity> userFoodFavs;

    @OneToMany(mappedBy = "user")
    private Set<UserResFavEntity> userResFavs;
}
