package com.lszpro.controller.system;


import com.lszpro.entity.bo.MenuBO;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.entity.common.MenuType;
import com.lszpro.entity.rq.MenuRq;
import com.lszpro.entity.rs.MenuRs;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/5/15 14:27
 * 菜单管理模块
 */
@Controller
public class MenuController {

    /* 列表查询 */
    @RequiresPermissions("system_menu")
    @GetMapping("/system/menu/list.php")
    public String list(HttpServletRequest request, Model model){
        StringBuilder initPageLink = new StringBuilder(request.getContextPath()).append("/system/menu/page.php");
        if(!StringUtils.isEmpty(request.getQueryString())){
            initPageLink.append("?").append(request.getQueryString()).toString();
        }
        model.addAttribute("initPageLink", initPageLink);
        Map<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
    	MenuRs response =  SoaConnectionFactory.get( ConstantsUri.Menu,map,MenuRs.class);
    	List<MenuBO> list = response.getDataList();
    	model.addAttribute("allMenu",list);
        return "system/menu/list";
    }

    /* 列表查询 */
    @GetMapping("/system/menu/page.php")
    public String page( HttpServletRequest request,Model model, MenuRq menuRq){
        menuRq.setSize(1111);
    	MenuRs menuRs =  SoaConnectionFactory.get(ConstantsUri.Menu,menuRq,MenuRs.class);
    	List<MenuBO> list = menuRs.getDataList();
        model.addAttribute("menus",list);

        menuRq.setTotalItems(menuRs.getTotal());
        menuRq.calculate();
        model.addAttribute("pagination", menuRq);
        return "system/menu/list_page";
    }


    /* 新增-修改 GET */
    @RequiresPermissions("system_menu")
    @GetMapping("/system/menu/edit.php")
    public String edit(@RequestParam(value = "currLink", defaultValue = "")String currLink,
                       @RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request,Model model){
        currLink = ("".equals(currLink)) ? request.getContextPath()+"/system/menu/list.php" : currLink;
        model.addAttribute("currLink",currLink);
        if(StringUtils.isNotEmpty(id)){
            MenuRs rs = SoaConnectionFactory.get(ConstantsUri.MenuInfo, null, MenuRs.class, id);
            model.addAttribute("menu",rs.getData());
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("page","0");
        map.put("size","0");
     	MenuRs response =  SoaConnectionFactory.get(ConstantsUri.Menu,map,MenuRs.class);
    	List<MenuBO> list = response.getDataList();
        model.addAttribute("menus",list);
        model.addAttribute("menuType", MenuType.values());
        return "system/menu/edit";
    }

    /* 新增-修改 POST */
    @RequiresPermissions("system_menu")
    @PostMapping(value = "/system/menu/save.php")
    public @ResponseBody
    BaseResponse add(HttpServletRequest request, @RequestBody MenuBO menu) throws IOException {
        BaseResponse rs = null;
        if(StringUtils.isEmpty(menu.getId())){
            rs = SoaConnectionFactory.post( ConstantsUri.Menu, menu, MenuRs.class);
        }else{
            rs = SoaConnectionFactory.put( ConstantsUri.MenuInfo, menu, MenuRs.class, menu.getId());
        }
        return rs;
    }

    /* 启用, 禁用 */
    @RequiresPermissions("system_menu")
    @PostMapping("/system/menu/enable.php")
    public @ResponseBody BaseResponse enable(@RequestParam(value = "id") String id,
                                          @RequestParam(value = "enable", defaultValue = "false") boolean enable, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("status",enable);
        return SoaConnectionFactory.put( ConstantsUri.MenuInfo, map, BaseResponse.class,id);
    }

    /* 删除 */
    @RequiresPermissions("system_menu")
    @PostMapping("/system/menu/delete/{id}.php")
    public @ResponseBody BaseResponse delete(@PathVariable(value = "id") String id, HttpServletRequest request){
        return SoaConnectionFactory.delete( ConstantsUri.MenuInfo, null, BaseResponse.class, id);
    }
}
