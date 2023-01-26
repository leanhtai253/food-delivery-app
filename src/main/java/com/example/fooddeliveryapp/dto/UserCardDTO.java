package com.example.fooddeliveryapp.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCardDTO {
    private int id;
    private int user;
    @NotBlank(message = "Company cannot be blank.")
    private String company;
    @NotBlank(message = "CardType cannot be blank.")
    private String cardType;
    @NotBlank(message = "Level cannot be blank.")
    private String level;
}
