package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.OrderUpComingDTO;
import com.example.fooddeliveryapp.entities.TOrderEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderUpComingMapper {
    public OrderUpComingDTO orderUpComingToDTO(TOrderEntity entity) {
        OrderUpComingDTO dto = new OrderUpComingDTO();
        dto.setId(entity.getId());
        dto.setEstimateShip(entity.getEstimateShip());

        return dto;
    }
}
