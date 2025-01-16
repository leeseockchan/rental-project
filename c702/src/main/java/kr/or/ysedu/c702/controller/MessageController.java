package kr.or.ysedu.c702.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @GetMapping("/message/{name}")
    @ResponseBody
    public String message(@PathVariable("name") String myName){
        return "message: "+myName ;
    }
}
