package com.example.fooddeliveryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailEntity {

    @Id
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @OneToOne()
    @JoinColumn(name = "id_user")
    private UserEntity user;
}
