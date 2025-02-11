package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDto> findAllInquiry();
}
