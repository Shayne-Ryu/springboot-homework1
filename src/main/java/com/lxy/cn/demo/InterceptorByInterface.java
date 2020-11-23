package com.lxy.cn.demo;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

//通过实现HandlerInterceptor接口实现拦截,这个类需要注册
public class InterceptorByInterface implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("USER");
        //获取请求的url
        String requestURI = request.getRequestURI();
        session.setAttribute("url", requestURI);
        System.out.println(requestURI);

        if (user != null && user != "") {
            System.out.println("用户已登录");
            return true;
        } else {
            System.out.println("用户未登录");
            response.sendRedirect("/login");
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
