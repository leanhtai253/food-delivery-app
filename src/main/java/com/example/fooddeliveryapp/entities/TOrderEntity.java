package com.example.fooddeliveryapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "estimate_ship")
    private String estimateShip;

    @Column(name = "deliver_address")
    private String deliverAddress;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "rating")
    private float rating;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "id_voucher")
    @JsonIgnore
    private VoucherEntity voucher;

    @ManyToOne()
    @JoinColumn(name = "id_payment")
    @JsonIgnore
    private PaymentEntity payment;

    @OneToMany(mappedBy = "tOrder")
    private Set<OrderStatusEntity> orderStatuses;

    @OneToMany(mappedBy = "tOrder")
    private Set<OrderDetailEntity> orderDetails;
}