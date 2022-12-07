package com.web_work_mxl.Service;

import com.web_work_mxl.Dao.CardDao;

import com.web_work_mxl.entity.Card;
import com.web_work_mxl.entity.ReturnStatu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardDao cardDao;

    public ReturnStatu get_count_card() {
        ReturnStatu returnStatu = new ReturnStatu();
        returnStatu.setStatu("200");
        Integer count_data = cardDao.get_count_data();
        returnStatu.setMessg("" + count_data);
        return returnStatu;
    }

    public ReturnStatu insert_data(HttpServletRequest req,Integer cardtype, String card_context, String card_timestack,String card_title){
        ReturnStatu messg = new ReturnStatu();
        HttpSession session = req.getSession();
        String account = (String) session.getAttribute("Users");
        if(account == null){
            messg.setMessg("你的请求异常，服务器已记录");
            messg.setStatu("404");
            return messg;
        }
        System.out.println(account+"  "+cardtype+"  "+card_context+"  "+card_timestack+"  "+card_title);
        try{
            Integer effect  = cardDao.insert_card(account, cardtype, card_context, card_timestack,card_title);
            messg.setStatu("200");
            messg.setMessg("信息发布成功！");
        }catch (Exception e){
            System.out.println(e);
            messg.setStatu("400");
            messg.setMessg("发布异常！");
        }
        return messg;
    }

    public List<Card> get_my_card(HttpServletRequest req){
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("Users");
        if (users == null) return null;
        return cardDao.get_my_card(users);
    }
    public void get_card_img(HttpServletResponse resp,Integer id) throws IOException {
        String path = "D:\\file_head";
        path+="\\"+id +".png";
        File file = new File(path);
        System.out.println(path);
        System.out.println(file.exists());
        if (file.exists()){
            InputStream inputStream = new FileInputStream(file);
            byte [] bytes = new byte[1024];
            Integer len;
            ServletOutputStream outputStream = resp.getOutputStream();
            while((len=inputStream.read(bytes))!= -1){
                outputStream.write(bytes,0,len);
            }
            inputStream.close();
            outputStream.close();
        }else{
            file = new File("D:\\file_profile"+"\\"+"404.gif");
            ServletOutputStream outputStream = resp.getOutputStream();
            InputStream inputStream = new FileInputStream(file);
            byte [] bytes = new byte[1024];
            Integer len ;
            while((len = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            outputStream.close();
            inputStream.close();

        }

    }
    public ReturnStatu delete_card(HttpServletRequest req,Integer id){
        ReturnStatu mesg = new ReturnStatu();
        mesg.setStatu("200");
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("Users");
        if (users == null){
            mesg.setStatu("400");
            mesg.setMessg("异常访问，服务器已记录您的访问ip和mac地址");
            return mesg;
        }

        Integer statu = cardDao.delete_card(users,id);
        if (statu == 1){
            mesg.setMessg("删除成功");
        }else if (statu == 0){
            mesg.setMessg("删除失败");
        }
        return mesg;
    }

    public List<Card> get_all_card(){
        return cardDao.get_all_card();
    }
}
