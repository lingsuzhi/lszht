package com.lszpro.controller.system;
 
import com.lszpro.common.MD5;
import com.lszpro.entity.bo.MenuBO;
import com.lszpro.entity.common.MenuType;
import com.lszpro.entity.rs.MenuNode;
import com.lszpro.entity.rs.MenuRs;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {
	  private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	    @PostMapping("/login.php")
	    public String login(@RequestParam(value = "username") String username,
	                        @RequestParam(value = "password") String password,
	                        HttpSession session, Model model) throws Exception{ 
	    	logger.info("username:{} , password:{}" ,username,password );
	    	UsernamePasswordToken token = new UsernamePasswordToken(username, MD5.md5(password));
	    	   try {
	               //验证是否登录成功
	               Subject currentUser = SecurityUtils.getSubject();
	               currentUser.login(token);

//	               UserBO userBO = (UserBO) currentUser.getPrincipal();
//	               session.setAttribute("currentUser", userBO);//没多大用
//	               logger.info("用户：【" + username + "】 登陆成功 ， 用户token:" + userBO.getLoginInfo().getToken());
//	               session.setAttribute("userToken", userBO.getLoginInfo().getToken());


	                return "redirect:/index";
	           } catch (Exception e) {
	               if (e instanceof IncorrectCredentialsException) {
	                   model.addAttribute("erro", "用户名或密码错误");
	               } else {
	                   model.addAttribute("erro", "系统异常");
	               }
	               token.clear();
	               return "login";
	           }
	    }
	    @GetMapping("/login/out.php")
	    public String out() {
	        SecurityUtils.getSubject().logout();
	        return "login";
	    }
	    
	    private List<MenuNode> getMenuParentNodes(List<MenuBO> list) {
	    	if (list == null) return null;
	        List<MenuNode> parentNodes = new ArrayList<>();
	        for (MenuBO menu : list) {
	            if (StringUtils.isEmpty(menu.getParentId()) && !MenuType.BUTTON.name().equals(menu.getType())) {
	                MenuNode parentNode = new MenuNode();
	                parentNode.setMenu(menu);
	                setChildNode(parentNode, list);
	                parentNodes.add(parentNode);
	            }
	        }
	        if(!parentNodes.isEmpty()){
	            sortMenuNode(parentNodes);
	        }
	        return parentNodes;
	    }

	    /* node表示本节点，list 代表所有菜单 */
	    private void setChildNode(MenuNode node, List<MenuBO> list) {
	        List<MenuNode> childNode = new ArrayList<>();
	        for (MenuBO m : list) {
	            if ((node.getMenu().getId()).equals(m.getParentId())) {
	                MenuNode n = new MenuNode();
	                n.setMenu(m);
	                setChildNode(n, list);
	                childNode.add(n);
	            }
	        }
	        if(!childNode.isEmpty()){
	            sortMenuNode(childNode);
	        }
	        node.setChildNode(childNode);
	    }

	    private void sortMenuNode(List<MenuNode> list){
	        Collections.sort(list, new Comparator<MenuNode>() {
	            public int compare(MenuNode n1, MenuNode n2) {
	                int sort1 = n1.getMenu().getSort();
	                int sort2 = n2.getMenu().getSort();
	                if (sort1 > sort2) {
	                    return 1;
	                } else if (sort1 == sort2) {
	                    return 0;
	                } else {
	                    return -1;
	                }
	            }
	        });
	    }
	    @RequestMapping("/main.htm")
	    public String mainhtm(){
	        return "main";
	    }
	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping("/index")
	    public String index(Model model){ 
	    	 List<MenuBO> userMenus = getMenus();
	    	 List<MenuNode> list = getMenuParentNodes(userMenus);
	        model.addAttribute("menuNodes",list );
	        return "index";
	    }
	    private List<MenuBO> getMenus(){
	    	Map map = new HashMap<String,String>();
	    	map.put("size","1111");
	    	MenuRs response =  SoaConnectionFactory.get(   ConstantsUri.Menu,map,MenuRs.class);
	    	List<MenuBO> list = response.getDataList();
	    	return list;
	    }
}
