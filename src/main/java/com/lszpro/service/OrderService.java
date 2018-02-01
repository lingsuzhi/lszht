package com.lszpro.service;

import com.lszpro.model.bo.OrderBO;
import com.lszpro.model.rq.OrderRq;
import com.lszpro.soa.ConstantsUri;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.soa.rs.OrderRs;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * 时间: 2018-1-19
 * 单据
 */
public interface OrderService {
    OrderRs orderDel(String id ) ;
    OrderRs orderById(String id );
    OrderRs orderDelByDanhao(String danho );

    OrderRs orderRight(String orderSetId,String id);
    OrderRs orderLeft(String orderSetId,String id);

    OrderRs orderSave( OrderBO OrderBO);
    OrderRs orderList(OrderRq rq);
    OrderBO dayEndOrde (String orderSetId);
}