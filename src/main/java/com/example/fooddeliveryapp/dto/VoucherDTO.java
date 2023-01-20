package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherDTO {
    private int id;
    private String promotionCode;
    private String description;
    private float value;
    private String startDate;
    private String endDate;
}
