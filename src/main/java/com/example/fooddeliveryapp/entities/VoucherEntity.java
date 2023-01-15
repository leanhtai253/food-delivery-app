package com.example.fooddeliveryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private float value;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @OneToMany(mappedBy = "voucher")
    private Set<TOrderEntity> tOrders;
}