package com.lszpro.application.interceptor;


import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lszpro.dto.MiniAdminDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginTimeInterceptor extends HandlerInterceptorAdapter{

    //在控制器执行前调用
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if(uri.endsWith(".op")){
            return true;
        }
        HttpSession session = request.getSession();
        MiniAdminDTO token = (MiniAdminDTO)session.getAttribute("token");
        if(token == null){
            request.getRequestDispatcher("/api/login.op").forward(request, response);
            return false;
        }
        return true;
    }
//    //在后端控制器执行后调用
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        System.out.println("执行postHandle方法-->02");
//        super.postHandle(request, response, handler, modelAndView);
//    }
//    //整个请求执行完成后调用
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        System.out.println("执行afterCompletion方法-->03");
//        super.afterCompletion(request, response, handler, ex);
//    }
}