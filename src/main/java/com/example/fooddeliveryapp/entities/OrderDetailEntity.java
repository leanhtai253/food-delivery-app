package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.entities.id.OrderDetailId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "order_details")
@IdClass(OrderDetailId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {

    @Id
    private int id_order;

    @Id
    private int id_food;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private TOrderEntity tOrder;

    @ManyToOne()
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    @JsonIgnore
    private FoodEntity food;
}