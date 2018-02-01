package com.lszpro.web;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.Configs;
import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.entity.rs.CustomerTypeNode;
import com.lszpro.exception.ServiceException;
import com.lszpro.model.bo.CustomerBO;
import com.lszpro.model.bo.CustomerBO;
import com.lszpro.model.bo.CustomerTypeBO;
import com.lszpro.model.rq.CustomerRq;
import com.lszpro.service.CustomerService;
import com.lszpro.service.CustomerTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerRs;
import com.lszpro.soa.rs.CustomerRs;
import com.lszpro.soa.rs.CustomerTypeRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-18
 * 网点
 */

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    protected static Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;
    @RequestMapping("/customerView.php")
    public String customerview(HttpServletRequest request, Model model, HttpSession session) {
        CustomerTypeRs rs = customerTypeService.customerTypeList(null);
        if (rs.isSuccess()) {
            List<CustomerTypeNode> rList =  getCustomerTypeParentNodes(rs.getDataList());

            model.addAttribute("tree", JSON.toJSONString(rList));

        }
        return "lsz/Customer_list";
    }

    @RequestMapping("/customerEdit.php")
    public String customerEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            CustomerRs rs = customerService.customerById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/Customer_edit";
    }

    @RequestMapping("/customerList.php")
    @ResponseBody
    public ResponseEntity customerList(CustomerRq rq, HttpServletRequest request, Model model, HttpSession session) {

        CustomerRs rs = customerService.customerList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/customerSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse customerSave(@RequestBody CustomerBO customerBO, HttpServletRequest request) {
        return customerService.customerSave(customerBO);
    }

    @RequestMapping("/customerById.php")
    @ResponseBody
    public BaseResponse customerById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return customerService.customerById(id);
    }

    @RequestMapping("/customerDel.php")
    @ResponseBody
    public BaseResponse customerDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return customerService.customerDel(id);

    }

    /** 设置头像 */
    @RequestMapping(value = "/setimg2", method = RequestMethod.POST)
    public @ResponseBody BaseResponse importPicFile1(@RequestParam MultipartFile img,
                                                     @RequestParam(required = true) String id, HttpServletRequest request) {
//暂时不删除旧文件，以后可以批处理
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("9999", "id错误");
        }
        // 原始名称
        String originalFilename = img.getOriginalFilename();
        // img.getSize() 大小
        // 上传图片
        if (img != null && originalFilename != null && originalFilename.length() > 0) {
            // 存储图片的物理路径
            String pic_path = Configs.FileDir;
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新图片
            pic_path = pic_path + "customerImg\\";

            File newFile = new File(pic_path + newFileName);
            // 判断目标文件所在的目录是否存在
            if (!newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！");
                if (!newFile.getParentFile().mkdirs()) {

                }
            }
            // 将内存中的数据写入磁盘
            try {
                img.transferTo(newFile);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            CustomerBO obj = new CustomerBO();
            obj.setId(id);
            obj.setImage("/imgs/customerImg/" + newFileName);
            CustomerRs returnObj = customerService.customerSave(obj);
            return returnObj;
        }
        throw new ServiceException("9999", "图片异常");
    }

    private List<CustomerTypeNode> getCustomerTypeParentNodes(List<CustomerTypeBO> list) {
        if (list == null) return null;
        List<CustomerTypeNode> parentNodes = new ArrayList<>();
        for (CustomerTypeBO CustomerType : list) {
            if (StringUtils.isEmpty(CustomerType.getParentId()) ) {
                CustomerTypeNode parentNode = new CustomerTypeNode();
                parentNode.setCustomerType(CustomerType);
                setChildNode(parentNode, list);
                parentNodes.add(parentNode);
            }
        }
        if(!parentNodes.isEmpty()){
            sortCustomerTypeNode(parentNodes);
        }
        return parentNodes;
    }

    /* node表示本节点，list 代表所有菜单 */
    private void setChildNode(CustomerTypeNode node, List<CustomerTypeBO> list) {
        List<CustomerTypeNode> childNode = new ArrayList<>();
        for (CustomerTypeBO m : list) {
            if ((node.getCustomerType().getId()).equals(m.getParentId())) {
                CustomerTypeNode n = new CustomerTypeNode();
                n.setCustomerType(m);
                setChildNode(n, list);
                childNode.add(n);
            }
        }
        if(!childNode.isEmpty()){
            sortCustomerTypeNode(childNode);
        }
        node.setChildren(childNode);
    }

    private void sortCustomerTypeNode(List<CustomerTypeNode> list){
        Collections.sort(list, new Comparator<CustomerTypeNode>() {
            public int compare(CustomerTypeNode n1, CustomerTypeNode n2) {
                Integer sort1 = n1.getCustomerType().getSort();
                Integer sort2 = n2.getCustomerType().getSort();
                if (sort1 == null) sort1 = 0;
                if (sort2 == null) sort2 = 0;

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
}