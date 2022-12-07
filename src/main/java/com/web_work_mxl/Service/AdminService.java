package com.web_work_mxl.Service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.web_work_mxl.Dao.Userdao;
import com.web_work_mxl.entity.ReturnStatu;
import com.web_work_mxl.entity.UserMessg;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    Userdao usedao;
    public ReturnStatu ban_user(HttpServletRequest req, String account){
        ReturnStatu mesg = new ReturnStatu();

        if(account == null){
            mesg.setStatu("404");
            mesg.setMessg("请输入用户名称！！");
            return mesg;
        }
        HttpSession session = req.getSession();
        String users = (String) session.getAttribute("Users");
        mesg.setStatu("200");
        System.out.println("users:"+users +" " +"accont:" + account);
        if(users == null) {
            mesg.setStatu("500");
            mesg.setMessg("非法请求！已向服务器记录此请求超过三次则禁止当前ip和mac访问！");
            return mesg;
        }
        UserMessg userMessg = usedao.SelectByAccount(users);
        if(userMessg == null){
            mesg.setStatu("404");
            mesg.setMessg("用户名称不存在！");
            return mesg;
        }

        if(userMessg.getPower() != 2){
            mesg.setMessg("error,require!");
            return mesg;
        }
        Integer statu = usedao.Updata_power(account, 0);
        mesg.setMessg(""+statu);
        return mesg;
    }

    public ReturnStatu  Excel_import(MultipartFile file) throws Exception {
        ReturnStatu returnStatu = new ReturnStatu();
        returnStatu.setStatu("400");
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        if(!split[1].equals("xlsx")){
            returnStatu.setMessg("非excel表格文件导入失败");
            return returnStatu;
        }
        ImportParams params = new ImportParams();
        List<UserMessg> objects = ExcelImportUtil.importExcel(file.getInputStream(), UserMessg.class, params);
        int num = 0;
        for (UserMessg i : objects){
            num++;
            if (i.getPower() == null || i.getEmail() == null || i.getAccount() == null || i.getPassword() == null || i.getTelephone() == null) {
                returnStatu.setMessg(returnStatu.getMessg()+"\n"+"第"+ num+"条记录插入失败，原因是字段中含有空值");
            }else{
                usedao.InsertUser(i);
            }
        }
        returnStatu.setStatu("200");
        returnStatu.setMessg(returnStatu.getMessg()+"\n"+"添加成功");
        return returnStatu;

    }

}
