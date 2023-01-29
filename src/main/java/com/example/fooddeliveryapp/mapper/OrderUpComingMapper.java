package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.OrderUpComingDTO;
import com.example.fooddeliveryapp.entities.OrderStatusEntity;
import com.example.fooddeliveryapp.entities.TOrderEntity;
import com.example.fooddeliveryapp.utils.Constant;
import org.springframework.stereotype.Service;

@Service
public class OrderUpComingMapper {
    public OrderUpComingDTO orderUpComingToDTO(TOrderEntity entity, int idStatus) {
        OrderUpComingDTO dto = new OrderUpComingDTO();
        dto.setId(entity.getId());
        dto.setEstimateShip(entity.getEstimateShip());

//        int maxStatus = 1;
//        for (OrderStatusEntity orderStatus: entity.getOrderStatuses()) {
//            if(orderStatus.getId_status() > maxStatus) {
//                maxStatus = orderStatus.getId_status();
//            }
//        }

        dto.setStatus(convertStatus(idStatus));
        return dto;
    }

    public String convertStatus(int idStatus) {
        String status = "";
        if(idStatus == 1) {
            status = Constant.CONFIRMED;
        } else if (idStatus == 2) {
            status = Constant.BEING_PREPARED;
        } else if (idStatus == 3) {
            status = Constant.ON_THE_WAY;
        } else if (idStatus == 4) {
            status = Constant.COMPLETED;
        } else {
            status = Constant.CANCEL;
        }
        return status;
    }
}
