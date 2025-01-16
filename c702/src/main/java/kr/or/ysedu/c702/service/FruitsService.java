package kr.or.ysedu.c702.service;

import kr.or.ysedu.c702.mapper.FruitsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitsService {

    @Autowired
    FruitsMapper fruitsMapper;

    public void addFruits(String name, int nuber){

    }
}
