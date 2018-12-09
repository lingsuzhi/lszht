package com.lszpro.api;

import com.alibaba.fastjson.TypeReference;
import com.lszpro.dto.LoginDTO;
import com.lszpro.dto.MiniAdminDTO;
import com.lszpro.entity.common.LayuiNavbarBO;
import com.lszpro.entity.rs.MenuNode;
import com.lszpro.soa.SoaFactory;
import com.lszpro.soa.common.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/api/loginDo.op")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpSession session, Model model) throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setName(username);
        loginDTO.setPwd(password);

        ResponseInfo<MiniAdminDTO> responseInfo = SoaFactory.post("登录", loginDTO, new TypeReference<ResponseInfo<MiniAdminDTO>>() {
        });
        if (responseInfo.isSuccess()) {
            session.setAttribute("token", responseInfo.getData());
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/loginOut")
    public String out(HttpSession session) {
        session.setAttribute("token", null);
        return "login";
    }

    @RequestMapping("/main.htm")
    public String mainhtm() {
        return "main";
    }

    @RequestMapping("/api/login.op")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String loginz() {
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
    @RequestMapping("/datas/navbar1.json")
    @ResponseBody
    public List<LayuiNavbarBO> navbar1() {
        ResponseInfo<List<LayuiNavbarBO>> responseInfo = SoaFactory.get("后台菜单", null, new TypeReference<ResponseInfo<List<LayuiNavbarBO>>>(){} );
        return responseInfo.getData();
    }
}
