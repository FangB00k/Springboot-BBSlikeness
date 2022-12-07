package com.web_work_mxl.entity;

public class Card {
  private Integer card_id;
  private String account;
  private Integer card_type;
  private String card_context;
  private String card_timestack;

  private String card_title;

  public String getCard_title() {
    return card_title;
  }

  public void setCard_title(String card_title) {
    this.card_title = card_title;
  }

  @Override
  public String toString() {
    return "Card{" +
            "card_id=" + card_id +
            ", account='" + account + '\'' +
            ", card_type=" + card_type +
            ", card_context='" + card_context + '\'' +
            ", card_timestack='" + card_timestack + '\'' +
            ", card_title='" + card_title + '\'' +
            '}';
  }


  public java.lang.Integer getCard_id() {
    return card_id;
  }

  public void setCard_id(java.lang.Integer card_id) {
    this.card_id = card_id;
  }

  public java.lang.String getAccount() {
    return account;
  }

  public void setAccount(java.lang.String account) {
    this.account = account;
  }

  public java.lang.Integer getCard_type() {
    return card_type;
  }

  public void setCard_type(java.lang.Integer card_type) {
    this.card_type = card_type;
  }

  public java.lang.String getCard_context() {
    return card_context;
  }

  public void setCard_context(java.lang.String card_context) {
    this.card_context = card_context;
  }

  public java.lang.String getCard_timestack() {
    return card_timestack;
  }

  public void setCard_timestack(java.lang.String card_timestack) {
    this.card_timestack = card_timestack;
  }
}
