package com.web_work_mxl.entity;

public class UserPaper {
    private String account;
    private Integer todaytime;
    private Integer punchout;
    private Integer todaytomato;

    @Override
    public String toString() {
        return "UserPaper{" +
                "account='" + account + '\'' +
                ", todaytime=" + todaytime +
                ", punchout=" + punchout +
                ", todaytomato=" + todaytomato +
                '}';
    }

    public UserPaper() {
    }

    public UserPaper(String account, Integer todaytime, Integer punchout, Integer todaytomato) {
        this.account = account;
        this.todaytime = todaytime;
        this.punchout = punchout;
        this.todaytomato = todaytomato;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getTodaytime() {
        return todaytime;
    }

    public void setTodaytime(Integer todaytime) {
        this.todaytime = todaytime;
    }

    public Integer getPunchout() {
        return punchout;
    }

    public void setPunchout(Integer punchout) {
        this.punchout = punchout;
    }

    public Integer getTodaytomato() {
        return todaytomato;
    }

    public void setTodaytomato(Integer todaytomato) {
        this.todaytomato = todaytomato;
    }
}
