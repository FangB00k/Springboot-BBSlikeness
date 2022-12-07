package com.web_work_mxl.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@RestController
//@RequestMapping("/test")
public class Test_Main {
    @GetMapping("/setcookie")
    public String set_cookie(HttpServletResponse res, String messg){
        Cookie a = new Cookie("messg",messg);
        res.addCookie(a);
        return "我好了";
    }

    @GetMapping("/getcookie")
    public String get_cookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie i : cookies){
            System.out.println(i.getName() + " " +i.getValue());
        }

        return "你猜猜我好没好";
    }
    @GetMapping("/session")
    public String get_session(HttpServletRequest req,String meesg){
        HttpSession session = req.getSession();
        session.setAttribute("messg",meesg);
        session.setAttribute("id",session.getLastAccessedTime());
        return "ok";
    }

    @GetMapping("/getsession")
    public String get_arrg_session(HttpServletRequest req){
        HttpSession session = req.getSession();
        String thesum = "";
        thesum+= session.getAttribute("messg");
        thesum+="  "+session.getAttribute("id");
        return thesum;
    }
}
