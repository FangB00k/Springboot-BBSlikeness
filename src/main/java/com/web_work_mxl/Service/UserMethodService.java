package com.web_work_mxl.Service;

import com.web_work_mxl.Dao.CardDao;
import com.web_work_mxl.entity.ReturnStatu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UserMethodService {
    // 用户发帖
    @Autowired
    CardDao cardDao;

    public ReturnStatu reseive_img_card(MultipartFile file) throws IOException {
        ReturnStatu returnStatu = new ReturnStatu();
        returnStatu.setStatu("400");

//        String resource_path = getClass().getResource("/").getPath()+"static";
        String resource_path = "D:/file_head";
        String receive_filename = file.getOriginalFilename();
        String[] split = receive_filename.split("\\.");
        System.out.println(receive_filename);
        if(!split[1].equals("png")){
            returnStatu.setMessg("上传附图失败,服务器仅支持png格式的图片,其他格式请等待后续更新！");
            return returnStatu;
        }

        try{
            Integer count_data = cardDao.get_count_data() +1;
            System.out.println("receive_count_Data:"+count_data);
            file.transferTo(new File(resource_path + "/"+count_data +".png"));
        }catch (Exception e){
            returnStatu.setMessg("附图上传失败!");
            return returnStatu;
        }
        returnStatu.setStatu("200");
        returnStatu.setMessg("上传成功！");

        /*
         *  帖子表设计
         *  -  帖子id
         *  - 发贴用户
         *  - 附图目录
         *  - 帖子内容
         *  - 帖子类型
         *  - 时间戳
         *   - 1 纯文字
         *   - 2 带一张图片
         * */
        return returnStatu;
    }

    public void get_profile(HttpServletResponse resp,String account) throws IOException {
        String path = "D:\\file_profile";
        path+="\\"+account +".png";
        File file = new File(path);
        if (file.exists()){
            file = new File("D:\\file_profile"+"\\"+ account+".png");
            ServletOutputStream outputStream = resp.getOutputStream();
            InputStream inputStream = new FileInputStream(file);
            byte [] bytes = new byte[1024];
            Integer len ;
            while((len = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            outputStream.close();
            inputStream.close();
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

    public ReturnStatu upload_profile(HttpServletRequest req,MultipartFile file) throws IOException {
        ReturnStatu returnStatu = new ReturnStatu();
        returnStatu.setStatu("400");

//        String resource_path = getClass().getResource("/").getPath()+"static";
        String resource_path = "D:\\file_profile";
        String receive_filename = file.getOriginalFilename();
        String[] split = receive_filename.split("\\.");
        System.out.println(receive_filename);
        if(!split[1].equals("png")){
            returnStatu.setMessg("上传附图失败,服务器仅支持png格式的图片,其他格式请等待后续更新！");
            return returnStatu;
        }

        try{
            HttpSession session = req.getSession();
            String users = (String) session.getAttribute("Users");
            if (users == null){
                returnStatu.setMessg("上传附图失败,服务器仅支持png格式的图片,其他格式请等待后续更新！");
                return returnStatu;
            }

            file.transferTo(new File(resource_path + "/"+users +".png"));
        }catch (Exception e){
            returnStatu.setMessg("附图上传失败!");
            return returnStatu;
        }
        returnStatu.setStatu("200");
        returnStatu.setMessg("上传成功！");
        return returnStatu;
    }




}
