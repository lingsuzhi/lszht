package com.lszpro.web;

import com.lszpro.common.LszUtil;

import com.lszpro.common.Utils;
import com.lszpro.model.bo.OrderBO;
import com.lszpro.model.rq.OrderRq;
import com.lszpro.service.OrderService;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.SoaConnectionFactory;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderRs;
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
 * 单据
 */

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    protected static Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @RequestMapping("/orderView.php")
    public String orderview(HttpServletRequest request, Model model, HttpSession session) {
        return "lsz/Order_list";
    }

    @RequestMapping("/orderEdit.php")
    public String orderEdit(@RequestParam(required = false) String id, HttpServletRequest request, Model model) {
        if (!StringUtils.isEmpty(id)) {
            OrderRs rs = orderService.orderById(id);
            model.addAttribute("obj", rs.getData());
        }
        return "lsz/Order_edit";
    }

    @RequestMapping("/orderList.php")
    @ResponseBody
    public ResponseEntity orderList(OrderRq rq, HttpServletRequest request, Model model, HttpSession session) {

        OrderRs rs = orderService.orderList(rq);
        if (rs.isSuccess()) {
            return LszUtil.returnList(rs.getDataList(), rs.getTotal());
        }
         return ResponseEntity.ok(rs);
    }

    @RequestMapping(value = "/orderSave.php", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse orderSave(@RequestBody OrderBO orderBO, HttpServletRequest request) {
        return orderService.orderSave(orderBO);
    }

    @RequestMapping("/orderById.php")
    @ResponseBody
    public BaseResponse orderById(@RequestParam(required = true) String id, HttpServletRequest request) {
        return orderService.orderById(id);
    }

    @RequestMapping("/orderDel.php")
    @ResponseBody
    public BaseResponse orderDel(@RequestParam(required = true) String id, HttpServletRequest request) {
         return orderService.orderDel(id);

    }
}