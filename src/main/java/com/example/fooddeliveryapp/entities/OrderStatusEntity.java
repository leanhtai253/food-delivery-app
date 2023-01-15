package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.entities.id.OrderStatusId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "order_status")
@IdClass(OrderStatusId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusEntity {
    @Id
    private int id_order;

    @Id
    private int id_status;

    @ManyToOne()
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private TOrderEntity tOrder;

    @ManyToOne()
    @JoinColumn(name = "id_status", insertable = false, updatable = false)
    @JsonIgnore
    private StatusEntity status;
}
