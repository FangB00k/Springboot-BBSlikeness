package com.web_work_mxl.entity;

public class SavePassword {
  private String username;
  private String password;
  private Integer statu;

    public SavePassword(String username, String password, Integer statu) {
        this.username = username;
        this.password = password;
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "SavePassword{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", statu=" + statu +
                '}';
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

}
