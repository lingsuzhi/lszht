package com.lszpro.controller.system;

import com.baidu.ueditor.ActionEnter;
import com.lszpro.common.Configs;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

/**
 * Created by Administrator on 2018/1/9 0009.
 */
@Controller
public class UeditController {
    @PostMapping("/ueditor/jsp/ueditorDispatch1")
    public void ueditorDispatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");


            URL s =  UeditController.class.getClassLoader().getResource("static");
            String rootPath = s.getPath();


            ActionEnter actionEnter = new ActionEnter(request, rootPath, Configs.FileDir);
            String exec = actionEnter.exec();

            response.getWriter().write(exec);
        } catch (Exception  e){
            e.printStackTrace();
        }
    }
    @GetMapping("/ueditor/jsp/ueditorDispatch")
    public void ueditorDispatchG1(HttpServletRequest request, HttpServletResponse response) {
        ueditorDispatch(request,response);
    }
}
