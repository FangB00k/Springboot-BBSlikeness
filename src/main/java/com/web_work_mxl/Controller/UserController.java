package com.web_work_mxl.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.web_work_mxl.Dao.Userdao;
import com.web_work_mxl.Service.AdminService;
import com.web_work_mxl.Service.CardService;
import com.web_work_mxl.Service.UserMethodService;
import com.web_work_mxl.entity.Card;
import com.web_work_mxl.entity.ReturnStatu;
import com.web_work_mxl.entity.SavePassword;
import com.web_work_mxl.entity.UserMessg;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import  com.web_work_mxl.Service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
   UserService userService;
   @Autowired
    CardService cardService;
    @Autowired
    AdminService adminService;
   @Autowired
    UserMethodService userMethodService;
//临时开放接口演示用
@PostMapping("/admin_excel")
public ReturnStatu exlce_import(MultipartFile file) throws Exception {
    System.out.println(file.getOriginalFilename());
    return adminService.Excel_import(file);
}
@PostMapping("/admin_ban/{id}")
public ReturnStatu admin_ban(@PathVariable("id")String id,HttpServletRequest request){
    return adminService.ban_user(request,id);
}

   // 登录接口
  @PostMapping("/login")
  public ReturnStatu login(HttpServletRequest req, HttpServletResponse res, @RequestParam("user") String Users, @RequestParam("password") String Password, @RequestParam(required = false) int is_save) throws IOException {
      return userService.UserLogin(req,res,Users,Password,is_save);
  }

  // 验证用户名存在接口
  @PostMapping("/isname")
  public Integer isname(String name){
     return userService.isname(name);
  }
  // 询问保存配置
 @GetMapping("/require_Config")
 public SavePassword read_Cookie(HttpServletRequest request){
      return userService.require_save(request);
 }

 // 注册接口
  @PostMapping("/register")
  public ReturnStatu register(UserMessg messg){
    return userService.UserRegister(messg);
  }
  @RequestMapping("/exit_user")
  public ReturnStatu exit_user(HttpServletRequest req){
      ReturnStatu rest = new ReturnStatu();
      rest.setStatu("200");
      rest.setMessg("退出账号成功");
      HttpSession session = req.getSession();
      session.invalidate();
      return rest;
  }
  @GetMapping("/getallcard")
  public List<Card> get_all_card(){
      return cardService.get_all_card();
  }
  @GetMapping("/get_login_statu")
  public Integer get_login_statu(HttpServletRequest request){
      return userService.get_login_statu(request);
  }

  @GetMapping("/getcard_img/{cardid}")
  public void get_card_img(HttpServletResponse response,@PathVariable("cardid") Integer cardid) throws IOException {
        cardService.get_card_img(response,cardid);
  }

  @RequestMapping("/get_profile/{account}")
  public void get_profile(HttpServletResponse response,@PathVariable("account") String account) throws IOException {
        userMethodService.get_profile(response, account);
  }
}
