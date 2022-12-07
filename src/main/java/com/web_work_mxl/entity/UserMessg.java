package com.web_work_mxl.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("users")
public class UserMessg {
    @Excel(name = "用户")
    private String account;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "权利")
    private Integer power;
    @Excel(name = "邮箱")
    private String email;
    @Excel(name = "电话")
    private String telephone;

    @Override
    public String toString() {
        return "UserMessg{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
