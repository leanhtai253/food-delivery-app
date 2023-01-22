package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherDTO {
    private String promotionCode;
    private String description;
    private float value;
}
