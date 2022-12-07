package com.web_work_mxl.Service;

import com.web_work_mxl.Dao.Userdao;
import com.web_work_mxl.entity.UserPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserPaperService {
    @Autowired
    private Userdao userdao;

    public UserPaper Get_Init_Messg(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("Users");
        String loginStatu = (String) session.getAttribute("LoginStatu");

        if (!loginStatu.equals("1") || users == null){
            // 判断黑客入侵了，非正常状态请求
            System.out.println("状态异常:" + users); // 服务器报警
            return new UserPaper();
        }
        System.out.println(userdao.Select_User_todaymessg(users));
        return userdao.Select_User_todaymessg(users);


    }
}
