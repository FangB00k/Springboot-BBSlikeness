package com.web_work_mxl.Controller;

import com.web_work_mxl.Service.UserPaperService;
import com.web_work_mxl.entity.UserPaper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/operate")
public class UserPaperController {
    @Autowired
    private UserPaperService userPaperService;

    @GetMapping("/hello")
    public String hello(){
        return  "Hello";
    }
    @GetMapping("/get_messg")
    public UserPaper get_messg(HttpServletResponse resp, HttpServletRequest req){
        return userPaperService.Get_Init_Messg(req,resp);
    }

}
