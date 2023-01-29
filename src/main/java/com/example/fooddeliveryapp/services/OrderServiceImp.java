package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.OrderPreviousDTO;
import com.example.fooddeliveryapp.dto.OrderUpComingDTO;
import com.example.fooddeliveryapp.entities.OrderStatusEntity;
import com.example.fooddeliveryapp.entities.TOrderEntity;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.mapper.OrderPreviousMapper;
import com.example.fooddeliveryapp.mapper.OrderUpComingMapper;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderUpComingMapper orderUpComingMapper;

    @Autowired
    OrderPreviousMapper orderPreviousMapper;

    @Override
    public List<OrderUpComingDTO> getOrderUpComing(String email) {
        UserEntity entityUser = userRepository.findByEmail(email);

        List<OrderUpComingDTO> dtos = new ArrayList<>();
        for (TOrderEntity entity: entityUser.getTOrders()) {
            int maxIdStatus = 1;
            for (OrderStatusEntity orderStatus: entity.getOrderStatuses()) {
                if(orderStatus.getId_status() > maxIdStatus) {
                    maxIdStatus = orderStatus.getId_status();
                }
            }
            if(maxIdStatus < 4)
                dtos.add(orderUpComingMapper.orderUpComingToDTO(entity, maxIdStatus));
        }
        return dtos;
    }

    @Override
    public List<OrderPreviousDTO> getOrderPrevious(String email){
        UserEntity entityUser = userRepository.findByEmail(email);
        List<OrderPreviousDTO> dtos = new ArrayList<>();
        for (TOrderEntity entity: entityUser.getTOrders()) {
            int maxIdStatus = 1;
            for (OrderStatusEntity orderStatus: entity.getOrderStatuses()) {
                if(orderStatus.getId_status() > maxIdStatus) {
                    maxIdStatus = orderStatus.getId_status();
                }
            }
            if(maxIdStatus >= 4)
                dtos.add(orderPreviousMapper.orderPreviousToDTO(entity, maxIdStatus));
        }
        return dtos;
    }
}
