package kr.or.ysedu.c702.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FruitsController {

    @GetMapping("/fruits/{name}")
    public String fruitsStroe(@PathVariable("name") String fruitsName){

        return ;
    }
}
