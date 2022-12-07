package com.web_work_mxl.Mapper;


import com.web_work_mxl.entity.Card;
import org.apache.ibatis.annotations.*;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Mapper
public interface CardMapper{
   @Select("SELECT MAX(`card_id`) FROM `user_card`")
   public  Integer Select_Data_Count();
   
   @Insert("INSERT INTO `user_card` ( `account`, `card_type`, " +
           "`card_context`, `card_timestack`,`card_title`) VALUES" +
           " (#{account}, #{cardtype}, #{card_context}, #{card_timestack},#{card_title})")
   public Integer Insert_Card(@Param("account") String account,@Param("cardtype") Integer cardtype,@Param("card_context") String card_context,@Param("card_timestack") String card_timestack,@Param("card_title") String card_title);

   @Select("SELECT * FROM `user_card` WHERE `account`  = #{account} ")
   public List<Card> get_my_card(@Param("account") String account);

   @Delete("DELETE  FROM  `user_card` WHERE `account` = #{account} and `card_id` = #{id}  ")
   public Integer delete_card(@Param("account") String account,@Param("id") Integer id);

   @Select("SELECT * FROM `user_card`")
   public List<Card> select_all_card();


}
