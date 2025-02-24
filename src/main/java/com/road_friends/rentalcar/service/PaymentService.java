package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PaymentDto;
import com.road_friends.rentalcar.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public void createPayment(PaymentDto paymentDto) {
        paymentMapper.insertPayment(paymentDto);
    };
}
