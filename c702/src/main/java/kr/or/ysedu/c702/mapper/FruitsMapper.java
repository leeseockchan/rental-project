package kr.or.ysedu.c702.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FruitsMapper {
    void insertFruits(String name, int number);
}
