package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.InquiryDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InquiryMapper {

    // 고객 질문 전체 조회 (페이지네이션)
    @Select("SELECT COUNT(*) FROM inquiries")
    int getTotalInquiries();

    // 고객 질문 조회 (페이지네이션)
    @Select("SELECT inquiries_num, user_num, inquiries_q, inquiries_q_created_at, inquiries_a, inquiries_a_created_at " +
            "FROM inquiries ORDER BY inquiries_num DESC LIMIT #{size} OFFSET #{offset}")
    List<InquiryDto> getPagedInquiries(@Param("offset") int offset, @Param("size") int size);

    // 고객 질문 상세 조회
    @Select("SELECT inquiries_num, user_num, inquiries_q, inquiries_q_created_at, inquiries_a, inquiries_a_created_at FROM inquiries WHERE inquiries_num = #{inquiryId}")
    InquiryDto findInquiryById(int inquiryId);

    // 고객 질문 추가
    @Insert("INSERT INTO inquiries (user_num, inquiries_q) VALUES (#{userNum}, #{inquiriesQ})")
    @Options(useGeneratedKeys = true, keyProperty = "inquiriesNum")
    void insertInquiry(InquiryDto inquiryDto);

    // 고객 질문 수정
    @Update("UPDATE inquiries SET inquiries_q = #{inquiriesQ} WHERE inquiries_num = #{inquiriesNum}")
    void updateInquiry(InquiryDto inquiryDto);

    // 고객 질문 삭제
    @Delete("DELETE FROM inquiries WHERE inquiries_num = #{inquiryId}")
    void deleteInquiry(int inquiryId);
}
