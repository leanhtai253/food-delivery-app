package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.VoucherDTO;
import com.example.fooddeliveryapp.entities.VoucherEntity;
import org.springframework.stereotype.Service;

@Service
public class VoucherMapper {
    public VoucherDTO voucherToVoucherDto(VoucherEntity entity) {
        VoucherDTO dto = new VoucherDTO();
        dto.setId(entity.getId());
        dto.setPromotionCode(entity.getPromotionCode());
        dto.setDescription(entity.getDescription());
        dto.setValue(entity.getValue());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        return dto;
    }
}
