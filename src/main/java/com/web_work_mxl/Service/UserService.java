package com.web_work_mxl.Service;

import com.web_work_mxl.Dao.Userdao;
import com.web_work_mxl.entity.ReturnStatu;
import com.web_work_mxl.entity.SavePassword;
import com.web_work_mxl.entity.UserMessg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    Userdao userdao;
    public SavePassword require_save(HttpServletRequest req){
        SavePassword read_messg = new SavePassword("","",0);
        Cookie[] cookies = req.getCookies();
        for(Cookie i : cookies){
            if(i.getName().equals( "username")){
                read_messg.setUsername(i.getValue());
            }else if(i.getName().equals("password")){
                read_messg.setPassword(i.getValue());
            }else if(i.getName().equals("issavemessg")){
                read_messg.setStatu(Integer.valueOf(i.getValue()));
            }
        }
        return read_messg;
    }
    public ReturnStatu  UserLogin(HttpServletRequest req, HttpServletResponse res, String Users, String Password, int is_save) throws IOException {
        ReturnStatu returnmessg = new ReturnStatu();
        System.out.println("is save:" + is_save +" " + Users + " " + Password);
        UserMessg userMessg;
        try{
            userMessg = userdao.SelectByAccount(Users);
        }catch (Exception e){
            userMessg = null;
        }

        if (is_save == 1){
          Cookie save_user = new Cookie("username",Users);
            Cookie save_password = new Cookie("password",Password);
            Cookie save_statu = new Cookie("issavemessg",""+is_save);
            res.addCookie(save_user);
            res.addCookie(save_statu);
            res.addCookie(save_password);

        }

        if (userMessg == null){
            returnmessg.setMessg("当前账户不存在请注册！");
            returnmessg.setStatu("400");
            return returnmessg;
        }else if(userMessg.getPower() == 0){

            returnmessg.setMessg("当前账户已被管理员封禁,请联系管理员了解详细情况");
            returnmessg.setStatu("404");
            return returnmessg;

        } else if(userMessg.getPassword().equals(Password)){
//        req.setAttribute("user",Users); 不能这样太危险了
//            Cookie cookie = new Cookie("user", Users);
//            cookie.setMaxAge(12*60*60);
//            res.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("LoginStatu","1");
            session.setAttribute("Users",Users);
            returnmessg.setMessg("登录成功");
            returnmessg.setStatu("200");
            //====跳转到主功能===

            //=================
            return returnmessg;
        }else{
            returnmessg.setMessg("登录失败，请检查是否密码正确！");
            returnmessg.setStatu("400");
            return returnmessg;
        }
    }
    public ReturnStatu  UserRegister(UserMessg messg){
        ReturnStatu returnmessg = new ReturnStatu();

        System.out.println(messg);
        returnmessg.setStatu("400");
        returnmessg.setMessg("注册失败");

        if (messg.getPassword()  == null || messg.getPassword().length() == 0){
            return returnmessg;
        }else if(messg.getAccount() == null || messg.getAccount().length() == 0){
            return returnmessg;
        }else if(messg.getTelephone() == null ||messg.getTelephone().length() == 0){
            return  returnmessg;
        }else if(messg.getEmail() == null || messg.getEmail().length() == 0){
            return returnmessg;
        }else{
            if (this.isname(messg.getAccount()) == 1){
                returnmessg.setMessg("非法请求服务器已记录本次请求");
                return returnmessg;
            }

            messg.setPower(1);
            userdao.InsertUser(messg);
            returnmessg.setStatu("200");
            returnmessg.setMessg("注册成功");
            return returnmessg;
        }

    }

    public Integer isname(String name){
        Integer thesum = userdao.Selet_Name_Count(name);
        if (thesum == 0) return 2;
        return  1;
    }




    public ReturnStatu password_update(HttpServletRequest req,String inputpassword,String newpassword){
        ReturnStatu returnStatu = new ReturnStatu();
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("Users");
        if (users == null ){
            returnStatu.setMessg("请求异常！");
            returnStatu.setStatu("404");
            return returnStatu;
        }
        UserMessg userMessg = userdao.SelectByAccount(users);
        if (userMessg == null ){
            returnStatu.setMessg("请求异常！");
            returnStatu.setStatu("404");
            return returnStatu;
        }

        if (inputpassword.equals(userMessg.getPassword())){
            userdao.update_password(users,newpassword);
            returnStatu.setMessg("更改密码成功");
            returnStatu.setStatu("200");
        }else{
            returnStatu.setMessg("原密码输入错误,更新失败");
            returnStatu.setStatu("200");
        }
        return returnStatu;

    }

    public Integer get_login_statu(HttpServletRequest req){
        HttpSession session = req.getSession();
        String loginStatu = (String) session.getAttribute("LoginStatu");
        if (loginStatu == null)return 0;
        if (loginStatu.equals("0"))return 0;
        return 1;


    }
}

