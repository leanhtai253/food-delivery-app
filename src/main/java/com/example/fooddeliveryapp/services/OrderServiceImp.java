package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.OrderPreviousDTO;
import com.example.fooddeliveryapp.dto.OrderUpComingDTO;
import com.example.fooddeliveryapp.dto.OrderedFoodDetailsDTO;
import com.example.fooddeliveryapp.entities.*;
import com.example.fooddeliveryapp.mapper.OrderPreviousMapper;
import com.example.fooddeliveryapp.mapper.OrderUpComingMapper;
import com.example.fooddeliveryapp.repositories.FoodRepository;
import com.example.fooddeliveryapp.repositories.TOrderRepository;
import com.example.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderUpComingMapper orderUpComingMapper;

    @Autowired
    OrderPreviousMapper orderPreviousMapper;

    @Autowired
    TOrderRepository tOrderRepository;

    @Autowired
    FoodRepository foodRepository;

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

    @Override
    public List<Object> getOrderPreviousDetails(String email, int id) {
        List<Object> returnData = new ArrayList<>();

        TOrderEntity thisOrder = this.tOrderRepository.findById(id);
        // Get delivery address
        returnData.add(thisOrder.getDeliverAddress());

        // Get food list
        Set<OrderDetailEntity> thisOrderFoodList = thisOrder.getOrderDetails();
        List<OrderedFoodDetailsDTO> thisOrderFoodDetails = new ArrayList<>();
        for(OrderDetailEntity orderDetail: thisOrderFoodList){
            thisOrderFoodDetails.add(new OrderedFoodDetailsDTO(orderDetail.getQuantity(),
                    findFoodNameByFoodId(orderDetail.getId_food()),orderDetail.getUnitPrice()));
        }

        returnData.add(thisOrderFoodDetails);


        // Get subtotal
        float subtotal = 0;
        for(OrderDetailEntity orderDetail: thisOrderFoodList){
            subtotal += (orderDetail.getUnitPrice()*orderDetail.getQuantity());
        }
        returnData.add(subtotal);

        // Get voucher
        float voucherValue = thisOrder.getVoucher().getValue();
        returnData.add(voucherValue);

        // Get total
        float total = subtotal - voucherValue;
        returnData.add(total);

        return returnData;
    }

    public String findFoodNameByFoodId(int id){
        String name = foodRepository.findById(id).getName();
        return name;
    }
}
