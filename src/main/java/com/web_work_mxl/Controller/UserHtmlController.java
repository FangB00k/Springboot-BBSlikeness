package com.web_work_mxl.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController

public class UserHtmlController {
  @GetMapping("/login")
  public void login(HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/login.html");
  }
  @GetMapping("/register")
  public void register(HttpServletResponse resp) throws IOException {
      resp.sendRedirect("/register.html");
  }
  @GetMapping("/index")
  public void index(HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/index.html");
  }

  @GetMapping("/user_screen")
  public void user_screen(HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/user.html");
  }

  @GetMapping("/user_center")
  public  void user_center(HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/userCenter.html");
  }
}
