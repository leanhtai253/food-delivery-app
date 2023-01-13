package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address_type")
    private int addressType;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private UserEntity user;
}