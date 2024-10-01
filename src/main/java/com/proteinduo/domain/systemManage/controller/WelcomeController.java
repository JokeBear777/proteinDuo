package com.proteinduo.domain.systemManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 웰컴! 페이지, 처음 뷰를 반환한다
 */
@Controller
public class WelcomeController {
    @GetMapping("/")  
    public String welcome() {
        return "welcome"; 
    }
}
