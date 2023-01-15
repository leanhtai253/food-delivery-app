package com.example.fooddeliveryapp.entities.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodMaterialId implements Serializable {
    private int id_food;
    private int id_material;
}
