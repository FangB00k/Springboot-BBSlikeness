package com.web_work_mxl.Filter;

import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebFilter(filterName = "loginFilter" ,urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
  public  static  final AntPathMatcher PATH_MATCHER = new AntPathMatcher(); // 路径比较的
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String [] urls = {
                "/",
                "/login.html",
                "/index.html",
                "/css/**",
                "/js/**",
                "/index",
                "/img/**",
                "/404.html",
                "/register.html",
                "/login",
                "register",
                "/user/**"
        };
        HttpSession session = request.getSession();
       String loginStatu = (String) session.getAttribute("LoginStatu");
//        System.out.println(url+" " + check_url(urls,url)) ;
        if (!check_url(urls,url)){
           if (loginStatu == null){
               response.sendRedirect("/404.html");
               return;
           }
           if (!loginStatu.equals("1")){
//                ServletOutputStream outputStream = response.getOutputStream();
//                InputStream input = new FileInputStream(new File(""));
//                int len;
//                byte [] reads = new byte[1024];
//                while((len = input.read(reads))!= -1){
//                    outputStream.write(reads,0,len);
//                }
//                input.close();
//                outputStream.close();
               response.sendRedirect("/404.html");
                return;
            }
        }

        filterChain.doFilter(request,response);

    }
   public boolean check_url(String[] urls,String url){
        for( String i: urls){
            if (PATH_MATCHER.match(i,url)){
                return  true;
            }
        }
        return false;
   }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
