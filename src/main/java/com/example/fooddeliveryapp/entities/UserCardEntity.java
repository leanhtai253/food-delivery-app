package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company")
    private String company;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "level")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;
}