package com.web_work_mxl.Dao;

import com.web_work_mxl.Mapper.CardMapper;

import com.web_work_mxl.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardDao {
  @Autowired
    CardMapper cardMapper;
  public Integer get_count_data(){
    return cardMapper.Select_Data_Count();
  }
  public Integer insert_card(String account,Integer cardtype,String card_context,String card_timestack,String card_title){
    return  cardMapper.Insert_Card(account,cardtype,card_context,card_timestack,card_title);
  }
  public List<Card> get_my_card(String account){
    return cardMapper.get_my_card(account);
  }

  public Integer delete_card(String account,Integer id){
    return cardMapper.delete_card(account,id);
  }

  public List<Card> get_all_card(){
    return cardMapper.select_all_card();
  }
}
