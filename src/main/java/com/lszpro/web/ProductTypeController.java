package com.lszpro.web;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.entity.rs.ProductTypeNode;
import com.lszpro.model.bo.ProductTypeBO;
import com.lszpro.model.rq.ProductTypeRq;
import com.lszpro.service.ProductTypeService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductTypeRs;
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
@RequestMapping(value = "/productType")
public class ProductTypeController {

    protected static Logger log = LoggerFactory.getLogger(ProductTypeController.class);
    @Autowired
    private ProductTypeService productTypeService;
    @RequestMapping("/productTypeView.php")
    public String productTypeview(HttpServletRequest request, Model model, HttpSession session) {
        ProductTypeRs rs = productTypeService.productTypeList(null);
        if (rs.isSuccess()) {
            List<ProductTypeNode>  rList =  getProductTypeParentNodes(rs.getDataList());

            model.addAttribute("tree",JSON.toJSONString(rList));

        }
        return "lsz/ProductType_list";
    }

    @RequestMapping("/productTypeEdit.php")
    public String productTypeEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            ProductTypeRs rs = productTypeService.productTypeById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/ProductType_edit";
    }

    @RequestMapping("/productTypeList.php")
    @ResponseBody
    public ResponseEntity productTypeList(ProductTypeRq rq, HttpServletRequest request, Model model, HttpSession session) {

        ProductTypeRs rs = productTypeService.productTypeList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/productTypeSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse productTypeSave(@RequestBody ProductTypeBO productTypeBO, HttpServletRequest request) {
        return productTypeService.productTypeSave(productTypeBO);
    }

    @RequestMapping("/productTypeById.php")
    @ResponseBody
    public BaseResponse productTypeById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return productTypeService.productTypeById(id);
    }

    @RequestMapping("/productTypeDel.php")
    @ResponseBody
    public BaseResponse productTypeDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return productTypeService.productTypeDel(id);

    }

    private List<ProductTypeNode> getProductTypeParentNodes(List<ProductTypeBO> list) {
        if (list == null) return null;
        List<ProductTypeNode> parentNodes = new ArrayList<>();
        for (ProductTypeBO ProductType : list) {
            if (StringUtils.isEmpty(ProductType.getParentId()) ) {
                ProductTypeNode parentNode = new ProductTypeNode();
                parentNode.setProductType(ProductType);
                setChildNode(parentNode, list);
                parentNodes.add(parentNode);
            }
        }
        if(!parentNodes.isEmpty()){
            sortProductTypeNode(parentNodes);
        }
        return parentNodes;
    }

    /* node表示本节点，list 代表所有菜单 */
    private void setChildNode(ProductTypeNode node, List<ProductTypeBO> list) {
        List<ProductTypeNode> childNode = new ArrayList<>();
        for (ProductTypeBO m : list) {
            if ((node.getProductType().getId()).equals(m.getParentId())) {
                ProductTypeNode n = new ProductTypeNode();
                n.setProductType(m);
                setChildNode(n, list);
                childNode.add(n);
            }
        }
        if(!childNode.isEmpty()){
            sortProductTypeNode(childNode);
        }
        node.setChildren(childNode);
    }

    private void sortProductTypeNode(List<ProductTypeNode> list){
        Collections.sort(list, new Comparator<ProductTypeNode>() {
            public int compare(ProductTypeNode n1, ProductTypeNode n2) {
                Integer sort1 = n1.getProductType().getSort();
                Integer sort2 = n2.getProductType().getSort();
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