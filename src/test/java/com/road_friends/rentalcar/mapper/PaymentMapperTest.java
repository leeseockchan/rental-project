package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void testInsertPayment() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId("PAYID-test");
        paymentDto.setPayerId("test");
        paymentDto.setReservationId(2L);
        paymentDto.setReservationType("fast");
        paymentDto.setAmount(new BigDecimal("200.0"));
        paymentDto.setCurrency("usd");
        paymentDto.setPaymentGateway("paypal");
        paymentDto.setState("approved");

        paymentMapper.insertPayment(paymentDto);
    }

}
