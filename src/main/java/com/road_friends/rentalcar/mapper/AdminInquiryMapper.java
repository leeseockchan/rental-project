package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    List<AdminInquiryDto> findAllInquiry();

    AdminInquiryDto findInquiryById(int inquiryId);

//    void updateInquiryReply(int inquiryId, int adminNum, String inquiriesA);

void updateInquiryReply(@Param("inquiryId") int inquiryId,
                        @Param("adminNum") int adminNum,
                        @Param("inquiriesA") String inquiriesA);

    void clearInquiryAnswer(int inquiryId);

    void deleteInquiry(int inquiryId);

}
