package com.lszpro.web;

import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.model.bo.ProductCompanyBO;
import com.lszpro.model.rq.ProductCompanyRq;
import com.lszpro.service.ProductCompanyService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.ProductCompanyRs;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2017-12-15
 */

@Controller
@RequestMapping(value = "/productCompany")
public class ProductCompanyController {

    protected static Logger log = LoggerFactory.getLogger(ProductCompanyController.class);
    @Autowired
    private ProductCompanyService productCompanyService;
    @RequestMapping("/productCompanyView.php")
    public String productCompanyview(HttpServletRequest request, Model model, HttpSession session) {
        return "lsz/ProductCompany_list";
    }

    @RequestMapping("/productCompanyEdit.php")
    public String productCompanyEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            ProductCompanyRs rs = productCompanyService.productCompanyById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/ProductCompany_edit";
    }

    @RequestMapping("/productCompanyList.php")
    @ResponseBody
    public ResponseEntity productCompanyList(ProductCompanyRq rq, HttpServletRequest request, Model model, HttpSession session) {

        ProductCompanyRs rs = productCompanyService.productCompanyList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/productCompanySave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse productCompanySave(@RequestBody ProductCompanyBO productCompanyBO, HttpServletRequest request) {
        return productCompanyService.productCompanySave(productCompanyBO);
    }

    @RequestMapping("/productCompanyById.php")
    @ResponseBody
    public BaseResponse productCompanyById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return productCompanyService.productCompanyById(id);
    }

    @RequestMapping("/productCompanyDel.php")
    @ResponseBody
    public BaseResponse productCompanyDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return productCompanyService.productCompanyDel(id);

    }
}