package com.web_work_mxl.Controller;

import com.web_work_mxl.Service.AdminService;
import com.web_work_mxl.Service.CardService;
import com.web_work_mxl.Service.UserMethodService;
import com.web_work_mxl.Service.UserService;
import com.web_work_mxl.entity.Card;
import com.web_work_mxl.entity.ReturnStatu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/method")
// 暂且空着
public class UserMethodController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    UserMethodService userMethodService;
    @Autowired
    CardService cardService;
    @RequestMapping("/post_img")
    public ReturnStatu post_img(MultipartFile file) throws IOException {
        return userMethodService.reseive_img_card(file);
    }

    @RequestMapping("/get_cardnumber")
    public ReturnStatu get_cardnumber(){
        return cardService.get_count_card();
    }
    @RequestMapping("/post_card")
    public ReturnStatu post_card(HttpServletRequest req,Integer cardtype,String card_context,String card_timestack,String card_title){
        return cardService.insert_data( req,cardtype, card_context, card_timestack,card_title);
    }

    @RequestMapping("/get_my_card")
    public List<Card> get_my_card(HttpServletRequest req){
        return cardService.get_my_card(req);
    }

    @RequestMapping("/delete_card/{id}")
    public ReturnStatu delete_card(HttpServletRequest req,@PathVariable("id")Integer id){
        return cardService.delete_card(req, id);
    }
    @RequestMapping("/post_profile")
    public  ReturnStatu post_profile(HttpServletRequest req,MultipartFile file) throws IOException {
        return userMethodService.upload_profile(req,file);
    }
    @RequestMapping("/get_profile/{account}")
    public void get_profile(HttpServletResponse response,@PathVariable("account") String account) throws IOException {
        userMethodService.get_profile(response, account);
    }
    @PostMapping("/update_password")
    public ReturnStatu password_update(HttpServletRequest req, String old, String news){
        return userService.password_update(req,old,news);
    }


}
