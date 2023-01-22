package com.example.fooddeliveryapp.services;

import com.example.fooddeliveryapp.dto.FoodViewDTO;
import com.example.fooddeliveryapp.dto.VoucherDTO;
import com.example.fooddeliveryapp.entities.FoodEntity;
import com.example.fooddeliveryapp.entities.VoucherEntity;
import com.example.fooddeliveryapp.mapper.VoucherMapper;
import com.example.fooddeliveryapp.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VoucherServiceImp implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    VoucherMapper voucherMapper;
    @Override
    public List<VoucherDTO> getAllVouchers() {
        List<VoucherEntity> voucherEntities = voucherRepository.findAll();
        List<VoucherDTO> vouchers = new ArrayList<>();
        for (VoucherEntity voucher : voucherEntities) {
            vouchers.add(voucherMapper.voucherToVoucherDTO(voucher));
        }

        return vouchers;
    }
}
