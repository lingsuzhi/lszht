package com.lszpro.web;

import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.model.bo.OrderSetBO;
import com.lszpro.model.rq.OrderSetRq;
import com.lszpro.service.OrderSetService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderSetRs;
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
 * 时间: 2018-1-19
 * 单据配置
 */

@Controller
@RequestMapping(value = "/orderSet")
public class OrderSetController {

    protected static Logger log = LoggerFactory.getLogger(OrderSetController.class);
    @Autowired
    private OrderSetService orderSetService;
    @RequestMapping("/orderSetView.php")
    public String orderSetview(HttpServletRequest request, Model model, HttpSession session) {
        return "lsz/OrderSet_list";
    }

    @RequestMapping("/orderSetEdit.php")
    public String orderSetEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            OrderSetRs rs = orderSetService.orderSetById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/OrderSet_edit";
    }

    @RequestMapping("/orderSetList.php")
    @ResponseBody
    public ResponseEntity orderSetList(OrderSetRq rq, HttpServletRequest request, Model model, HttpSession session) {

        OrderSetRs rs = orderSetService.orderSetList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/orderSetSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse orderSetSave(@RequestBody OrderSetBO orderSetBO, HttpServletRequest request) {
        return orderSetService.orderSetSave(orderSetBO);
    }

    @RequestMapping("/orderSetById.php")
    @ResponseBody
    public BaseResponse orderSetById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return orderSetService.orderSetById(id);
    }

    @RequestMapping("/orderSetDel.php")
    @ResponseBody
    public BaseResponse orderSetDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return orderSetService.orderSetDel(id);

    }
}