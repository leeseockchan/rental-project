package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InquiryMapper {

    List<InquiryDto> findAllInquiry();

    InquiryDto findInquiryById(int inquiryId);

    void insertInquiry(InquiryDto inquiryDto);

    void updateInquiry(InquiryDto inquiryDto);

    void deleteInquiry(int inquiryId);
}
