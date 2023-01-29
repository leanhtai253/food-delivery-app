package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.OrderPreviousDTO;
import com.example.fooddeliveryapp.entities.OrderDetailEntity;
import com.example.fooddeliveryapp.entities.TOrderEntity;
import com.example.fooddeliveryapp.repositories.FoodRepository;
import com.example.fooddeliveryapp.repositories.UserRepository;
import com.example.fooddeliveryapp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderPreviousMapper {
    @Autowired
    FoodRepository foodRepository;

    public OrderPreviousDTO orderPreviousToDTO(TOrderEntity entity, int idStatus){
        OrderPreviousDTO dto = new OrderPreviousDTO();
        dto.setId(entity.getId());
        dto.setStatus(convertStatus(idStatus));
        dto.setRating(entity.getRating());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setFoodList(getFoodDetailNames(getFoodDetailIds(entity.getOrderDetails())));
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

    public List<Integer> getFoodDetailIds(Set<OrderDetailEntity> orderDetailSet){
        List<Integer> foodDetailIds = new ArrayList<>();
        for(OrderDetailEntity orderDetail : orderDetailSet){
            foodDetailIds.add(orderDetail.getId_food());
        }
        return foodDetailIds;
    }

    public List<String> getFoodDetailNames(List<Integer> foodDetailsIds){
        List<String> foodDetailsNames = new ArrayList<>();
        for(int foodDetailsId : foodDetailsIds){
            foodDetailsNames.add(foodRepository.findById(foodDetailsId).getName());
        }
        return foodDetailsNames;
    }
}
