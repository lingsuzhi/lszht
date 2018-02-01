package com.lszpro.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 拦截器：记录用户操作日志，检查用户是否登录…… 
 * @author XuJijun 
 */  
@Aspect  
@Component  
public class ControllerInterceptor {  
     @Pointcut("execution(* com.lszpro.controller..*(..))")// and @annotation(org.springframework.web.bind.annotation.RequestMapping)
    public void controllerMethodPointcut(){}  
      
    /** 
     * 拦截器具体实现 
     * @param pjp 
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。） 
     */  
    @Before("controllerMethodPointcut()") // @Around(    拦截  
    public void Interceptor(JoinPoint pjp){   
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        System.out.println(  sdf.format(date) +" Web-->"
    	+ pjp.getSignature().toShortString()   	
        		);
        
    }  

      

}  