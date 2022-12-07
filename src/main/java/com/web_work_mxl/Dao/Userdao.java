package com.web_work_mxl.Dao;

import com.web_work_mxl.Mapper.UserMapper;
import com.web_work_mxl.entity.UserMessg;
import com.web_work_mxl.entity.UserPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Userdao {
  @Autowired
  UserMapper usermapper;
  public UserMessg SelectByAccount(String account){
    return  usermapper.SelectUserMessage(account);
  }

  public Integer InsertUser(UserMessg users){
    return  usermapper.AddAccount(users);
  }
  public Integer Updata_power(String account,int powers){
    return  usermapper.Updata_power(account,powers);
  }

  public Integer Selet_Name_Count(String account){

    try{
      return usermapper.Select_Name_Count(account);
    }catch (Exception e){
      return  0;
    }

  }

  public UserPaper Select_User_todaymessg(String account){
    // UserPaperService
    return usermapper.Select_usercent_meesg(account);

  }

  public Integer update_password(String account,String newpassword){
     return usermapper.Update_password(account,newpassword);
  }



}
