package com.lszpro.web;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.entity.rs.CustomerTypeNode;
import com.lszpro.model.bo.CustomerTypeBO;
import com.lszpro.model.rq.CustomerTypeRq;
import com.lszpro.service.CustomerTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.CustomerTypeRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2017-12-15
 */

@Controller
@RequestMapping(value = "/customerType")
public class CustomerTypeController {

    protected static Logger log = LoggerFactory.getLogger(CustomerTypeController.class);
    @Autowired
    private CustomerTypeService customerTypeService;
    @RequestMapping("/customerTypeView.php")
    public String customerTypeview(HttpServletRequest request, Model model, HttpSession session) {
        CustomerTypeRs rs = customerTypeService.customerTypeList(null);
        if (rs.isSuccess()) {
            List<CustomerTypeNode>  rList =  getCustomerTypeParentNodes(rs.getDataList());

            model.addAttribute("tree", JSON.toJSONString(rList));

        }
        
        return "lsz/CustomerType_list";
    }

    @RequestMapping("/customerTypeEdit.php")
    public String customerTypeEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            CustomerTypeRs rs = customerTypeService.customerTypeById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/CustomerType_edit";
    }

    @RequestMapping("/customerTypeList.php")
    @ResponseBody
    public ResponseEntity customerTypeList(CustomerTypeRq rq, HttpServletRequest request, Model model, HttpSession session) {

        CustomerTypeRs rs = customerTypeService.customerTypeList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/customerTypeSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse customerTypeSave(@RequestBody CustomerTypeBO customerTypeBO, HttpServletRequest request) {
        return customerTypeService.customerTypeSave(customerTypeBO);
    }

    @RequestMapping("/customerTypeById.php")
    @ResponseBody
    public BaseResponse customerTypeById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return customerTypeService.customerTypeById(id);
    }

    @RequestMapping("/customerTypeDel.php")
    @ResponseBody
    public BaseResponse customerTypeDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return customerTypeService.customerTypeDel(id);

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