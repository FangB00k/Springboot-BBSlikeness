package com.web_work_mxl.entity;

import org.springframework.stereotype.Component;

@Component
public class ReturnStatu {
    private String statu;
    private String messg;
    @Override
    public String toString() {
        return "ReturnStatu{" +
                "statu='" + statu + '\'' +
                ", messg='" + messg + '\'' +
                '}';
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getMessg() {
        return messg;
    }

    public void setMessg(String messg) {
        this.messg = messg;
    }
}
