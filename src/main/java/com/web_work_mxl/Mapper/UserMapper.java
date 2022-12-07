package com.web_work_mxl.Mapper;

import com.web_work_mxl.entity.UserMessg;
import com.web_work_mxl.entity.UserPaper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 人傻了这边没写完sql
    @Select("SELECT * FROM `user_messg` WHERE`account` =  #{user_account}")
    public UserMessg SelectUserMessage(String user_account); // 根据用户名查询用户信息
    @Insert("INSERT INTO `user_messg` (`account`, `password`, `power`, `email`, `telephone`) VALUES (#{account}, #{password}, #{power}, #{email}, #{telephone})")
    public int AddAccount(UserMessg users); // 添加用户
    @Update("UPDATE `user_messg` SET `power` = #{power} WHERE `account` = #{account}")
    public int Updata_power(@Param("account") String account,@Param("power") int power); // 更改用户权限

    @Select("SELECT  COUNT(*) FROM `user_messg` WHERE`account` =  #{account}")
    public Integer Select_Name_Count(@Param("account") String account); //验证用户是否存在

    @Select("SELECT * FROM `user_cent` WHERE account = #{account}")
    public UserPaper Select_usercent_meesg(@Param("account")String account); // 查看用户使用信息

    @Update("UPDATE `user_messg` SET `password` = #{newpassword}  WHERE `account` = #{account}")
    public  Integer Update_password(@Param("account") String account,@Param("newpassword") String newpassword);


}
