package com.web_work_mxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebWorkMxlApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebWorkMxlApplication.class, args);
    }

}
