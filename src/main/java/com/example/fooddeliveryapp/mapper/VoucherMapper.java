package com.example.fooddeliveryapp.mapper;

import com.example.fooddeliveryapp.dto.UserDTO;
import com.example.fooddeliveryapp.dto.VoucherDTO;
import com.example.fooddeliveryapp.entities.UserEntity;
import com.example.fooddeliveryapp.entities.VoucherEntity;
import org.springframework.stereotype.Service;

@Service
public class VoucherMapper {
    public VoucherDTO voucherToVoucherDTO(VoucherEntity voucher){
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setPromotionCode(voucher.getPromotionCode());
        voucherDTO.setDescription(voucher.getDescription());
        voucherDTO.setValue(voucher.getValue());

        return voucherDTO;
    }
}
