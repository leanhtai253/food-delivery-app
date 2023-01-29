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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "token", nullable = true)
    private String token;

    @Column(name = "type_token", nullable = true)
    private String typeToken;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "verify_code", nullable = true)
    private String verifyCode;

    @Column(name = "verify_code_expired", nullable = true)
    private String verifyCodeExpired;

    @Column(name = "is_active", nullable = false)
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
