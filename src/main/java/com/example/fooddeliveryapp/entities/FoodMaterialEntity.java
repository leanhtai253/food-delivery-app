package com.example.fooddeliveryapp.entities;

import com.example.fooddeliveryapp.entities.id.FoodMaterialId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "food_material")
@IdClass(FoodMaterialId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodMaterialEntity {

    @Id
    private int id_food;

    @Id
    private int id_material;

    @ManyToOne()
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    @JsonIgnore
    private FoodEntity food;

    @ManyToOne()
    @JoinColumn(name = "id_material", insertable = false, updatable = false)
    @JsonIgnore
    private MaterialEntity material;
}