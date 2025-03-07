package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

  void insertPayment(PaymentDto paymentDto);

}
